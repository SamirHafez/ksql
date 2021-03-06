/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License; you may not use this file
 * except in compliance with the License.  You may obtain a copy of the License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.parser.tree;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.Objects;
import java.util.Optional;

public class TableSubquery
    extends QueryBody {

  private final Query query;

  public TableSubquery(final Query query) {
    this(Optional.empty(), query);
  }

  public TableSubquery(final NodeLocation location, final Query query) {
    this(Optional.of(location), query);
  }

  private TableSubquery(final Optional<NodeLocation> location, final Query query) {
    super(location);
    this.query = query;
  }

  public Query getQuery() {
    return query;
  }

  @Override
  public <R, C> R accept(final AstVisitor<R, C> visitor, final C context) {
    return visitor.visitTableSubquery(this, context);
  }

  @Override
  public String toString() {
    return toStringHelper(this)
        .addValue(query)
        .toString();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final TableSubquery tableSubquery = (TableSubquery) o;
    return Objects.equals(query, tableSubquery.query);
  }

  @Override
  public int hashCode() {
    return query.hashCode();
  }
}
