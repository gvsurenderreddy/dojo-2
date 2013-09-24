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
package br.com.objectos.dojo.taguiar;

import com.google.inject.Inject;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Post;
import com.google.sitebricks.http.Put;

/**
 * @author anderson.silva@objectos.com.br (Anderson Amorim Silva)
 */
public class ControllerDeAluno {

  private final FormDeAlunoCreate form;

  private final FormDeAlunoUpdate forms;

  @Inject
  public ControllerDeAluno(FormDeAlunoCreate form, FormDeAlunoUpdate forms) {
    this.form = form;
    this.forms = forms;
  }

  @Post
  public Reply<?> post() {
    return form.post();
  }

  @Put
  public Reply<?> put() {
    return forms.put();
  }

}