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
package org.sonar.server.computation.task.projectanalysis.period;

import javax.annotation.CheckForNull;

import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;

public class PeriodsHolderImpl implements PeriodsHolder {

  @CheckForNull
  private Period period = null;

  /**
   * Initializes the periods in the holder.
   *
   * @throws NullPointerException if the specified period is {@code null}
   * @throws IllegalStateException if the holder has already been initialized
   */
  public void setPeriod(Period newPeriod) {
    requireNonNull(period, "Period cannot be null");
    checkState(this.period == null, "Periods have already been initialized");
    this.period = newPeriod;
  }

  @Override
  public Period getPeriod() {
    checkHolderIsInitialized();
    return period;
  }

  @Override
  public boolean hasPeriod() {
    checkHolderIsInitialized();
    return period != null;
  }

  private void checkHolderIsInitialized() {
    checkState(this.period != null, "Periods have not been initialized yet");
  }

}
