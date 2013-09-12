/*
 * Copyright 2013 Objectos, Fábrica de Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.com.objectos.dojo.asilva;

import java.util.List;

import br.com.objectos.comuns.base.cache.CacheBuilder;
import br.com.objectos.dojo.cpetreanu.BuscarFuncionario;
import br.com.objectos.dojo.cpetreanu.Funcionario;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheLoader.InvalidCacheLoadException;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
@Singleton
class CacheDeFuncionarioGuice implements CacheDeFuncionario {

  private final LoadingCache<Integer, Funcionario> idCache;

  private final LoadingCache<SuperiorKey, List<Funcionario>> superiorKeyCache;

  private final BuscarFuncionario buscarFuncionario;

  @Inject
  public CacheDeFuncionarioGuice(CacheBuilder cacheBuilder, BuscarFuncionario buscarFuncionario) {
    this.idCache = cacheBuilder
        .small()
        .build(new IdLoader());
    this.superiorKeyCache = cacheBuilder
        .small()
        .build(new SuperiorKeyLoader());
    this.buscarFuncionario = buscarFuncionario;
  }

  @Override
  public Optional<Funcionario> porId(int id) {
    try {
      Funcionario pojo = idCache.getUnchecked(id);
      return Optional.of(pojo);
    } catch (InvalidCacheLoadException e) {
      return Optional.absent();
    }
  }

  @Override
  public List<Funcionario> porSuperiorKey(SuperiorKey key) {
    return superiorKeyCache.getUnchecked(key);
  }

  private class IdLoader extends CacheLoader<Integer, Funcionario> {
    @Override
    public Funcionario load(Integer id) throws Exception {
      return buscarFuncionario.porId(id);
    }
  }

  private class SuperiorKeyLoader extends CacheLoader<SuperiorKey, List<Funcionario>> {
    @Override
    public List<Funcionario> load(SuperiorKey key) throws Exception {
      return buscarFuncionario.porSuperiorKey(key);
    }
  }

}