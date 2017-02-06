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
package org.sonar.server.computation.task.projectanalysis.formula.coverage;

import com.google.common.base.Optional;
import org.sonar.server.computation.task.projectanalysis.formula.CounterInitializationContext;
import org.sonar.server.computation.task.projectanalysis.measure.Measure;
import org.sonar.server.computation.task.projectanalysis.measure.MeasureVariations;

import static org.sonar.server.computation.task.projectanalysis.formula.coverage.CoverageUtils.getLongVariation;

public final class LinesAndConditionsWithUncoveredVariationCounter extends ElementsAndCoveredElementsVariationCounter {
  private final LinesAndConditionsWithUncoveredMetricKeys metricKeys;

  public LinesAndConditionsWithUncoveredVariationCounter(LinesAndConditionsWithUncoveredMetricKeys metricKeys) {
    this.metricKeys = metricKeys;
  }

  @Override
  public void initializeForSupportedLeaf(CounterInitializationContext counterContext) {
    Optional<Measure> newLinesMeasure = counterContext.getMeasure(metricKeys.getLines());
    if (!newLinesMeasure.isPresent() || !newLinesMeasure.get().hasVariations()) {
      return;
    }

    MeasureVariations newLines = newLinesMeasure.get().getVariations();
    MeasureVariations newConditions = CoverageUtils.getMeasureVariations(counterContext, metricKeys.getConditions());
    MeasureVariations uncoveredLines = CoverageUtils.getMeasureVariations(counterContext, metricKeys.getUncoveredLines());
    MeasureVariations uncoveredConditions = CoverageUtils.getMeasureVariations(counterContext, metricKeys.getUncoveredConditions());
    if (!newLines.hasVariation(1)) {
      return;
    }
    long elements = (long) newLines.getVariation(1) + getLongVariation(newConditions);
    this.elements.increment(elements);
    coveredElements.increment(elements - getLongVariation(uncoveredConditions) - getLongVariation(uncoveredLines));
  }
}
