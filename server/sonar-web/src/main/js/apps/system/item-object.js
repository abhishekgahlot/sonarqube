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
import React from 'react';
import ItemValue from './item-value';

export default React.createClass({
  render () {
    const rows = Object.keys(this.props.value).map(key => {
      return (
          <tr key={key}>
            <td className="thin nowrap">{key}</td>
            <td><ItemValue value={this.props.value[key]}/></td>
          </tr>
      );
    });
    return (
        <table className="data">
          <tbody>{rows}</tbody>
        </table>
    );
  }
});
