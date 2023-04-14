/*
 * Copyright (C) 2010-2018 Gordon Fraser, Andrea Arcuri and EvoSuite
 * contributors
 *
 * This file is part of EvoSuite.
 *
 * EvoSuite is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * EvoSuite is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with EvoSuite. If not, see <http://www.gnu.org/licenses/>.
 */
package org.evosuite.runtime.mock.javax.swing;

import org.evosuite.runtime.Runtime;
import org.evosuite.runtime.RuntimeSettings;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.vfs.VirtualFileSystem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.File;

public class MockJFileChooserTest {

    private static final boolean VFS = RuntimeSettings.useVFS;

    @Before
    public void init() {
        RuntimeSettings.useVFS = true;
        Runtime.getInstance().resetRuntime();
    }

    @After
    public void restoreProperties() {
        RuntimeSettings.useVFS = VFS;
    }

    @Test
    public void testGetCurrentDirectory() {

        JFileChooser chooser = new MockJFileChooser(VirtualFileSystem.getWorkingDirPath()); // this falls back to home for the current directory, need to tell it curent directoy.
        File dir = chooser.getCurrentDirectory(); // this should pass because the current dirrectory is added to the filesystem.
        // but mockview behavior as this returning home.

        Assert.assertTrue(dir.exists());
        Assert.assertTrue(dir instanceof MockFile);
    }

}
