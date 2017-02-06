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

/**
 * Repository of the leak period used to compute differential measures.
 * Here are the steps to retrieve these periods :
 * - Read the period property ${@link org.sonar.core.config.CorePropertyDefinitions#TIMEMACHINE_PERIOD_PREFIX}
 * - Try to find the matching snapshots from the property
 * - If a snapshot is found, the period is set to the repository
 */
public interface PeriodsHolder {

  /**
   * Return the leak period
   *
   * @throws IllegalStateException if the period haven't been initialized
   */
  Period getPeriod();

  /**
   * Finds out whether the holder contains a Period for the specified index.
   *
   * @throws IllegalStateException if the periods haven't been initialized
   * @throws IndexOutOfBoundsException if i is either &lt; 1 or &gt; 5
   */
  boolean hasPeriod();

}
