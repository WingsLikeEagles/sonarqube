/*
 * SonarQube
 * Copyright (C) 2009-2021 SonarSource SA
 * mailto:info AT sonarsource DOT com
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
package org.sonar.server.platform.db.migration.version.v84.users.fk.organizationmembers;

import java.sql.SQLException;
import org.junit.Rule;
import org.junit.Test;
import org.sonar.db.CoreDbTester;
import org.sonar.server.platform.db.migration.step.DdlChange;

public class DropIndexOnUserIdOfOrganizationMembersTableTest {

  private static final String TABLE_NAME = "organization_members";
  private static final String INDEX_NAME = "ix_org_members_on_user_id";

  @Rule
  public CoreDbTester dbTester = CoreDbTester.createForSchema(DropIndexOnUserIdOfOrganizationMembersTableTest.class, "schema.sql");

  DdlChange underTest = new DropIndexOnUserIdOfOrganizationMembersTable(dbTester.database());

  @Test
  public void add_index() throws SQLException {
    underTest.execute();
    dbTester.assertIndexDoesNotExist(TABLE_NAME, INDEX_NAME);
  }

  @Test
  public void migration_is_reentrant() throws SQLException {
    underTest.execute();
    dbTester.assertIndexDoesNotExist(TABLE_NAME, INDEX_NAME);
  }

}
