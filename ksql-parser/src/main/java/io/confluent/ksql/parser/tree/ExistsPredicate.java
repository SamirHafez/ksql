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

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

public class ExistsPredicate
    extends Expression {

  private final Query subquery;

  public ExistsPredicate(final Query subquery) {
    this(Optional.empty(), subquery);
  }

  public ExistsPredicate(final NodeLocation location, final Query subquery) {
    this(Optional.of(location), subquery);
  }

  private ExistsPredicate(final Optional<NodeLocation> location, final Query subquery) {
    super(location);
    requireNonNull(subquery, "subquery is null");
    this.subquery = subquery;
  }

  public Query getSubquery() {
    return subquery;
  }

  @Override
  public <R, C> R accept(final AstVisitor<R, C> visitor, final C context) {
    return visitor.visitExists(this, context);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final ExistsPredicate that = (ExistsPredicate) o;
    return Objects.equals(subquery, that.subquery);
  }

  @Override
  public int hashCode() {
    return subquery.hashCode();
  }
}
