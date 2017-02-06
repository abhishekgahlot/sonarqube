/*
 * SonarQube
 * Copyright (C) 2009-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.computation.task.projectanalysis.formula.counter;

import javax.annotation.Nullable;

/**
 * Convenience class wrapping a double to compute a value as an double and know it is has ever been set.
 */
public class DoubleCounter {
  private boolean set = false;
  private double value = 0L;

  /**
   * @return the current {@link DoubleCounter} so that chained calls on a specific {@link DoubleCounter}  instance can be done
   */
  public DoubleCounter increment(double increment) {
    this.value += increment;
    this.set = true;
    return this;
  }

  /**
   * @return the current {@link DoubleCounter}  so that chained calls on a specific {@link DoubleCounter}  instance can be done
   */
  public DoubleCounter increment(@Nullable DoubleCounter value) {
    if (value != null && value.isSet()) {
      increment(value.value);
    }
    return this;
  }

  public boolean isSet() {
    return set;
  }

  public double getValue() {
    return value;
  }

}
