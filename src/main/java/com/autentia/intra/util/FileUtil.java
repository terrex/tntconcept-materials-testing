/*
 *  TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.
 *	Copyright (C) 2007 Autentia Real Bussiness Solution S.L.
 *
 * 	This program is free software; you can redistribute it and/or
 * 	modify it under the terms of the GNU General Public License
 * 	as published by the Free Software Foundation; either version 2
 * 	of the License, or (at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 *
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program; if not, write to the Free Software
 * 	Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * 	Autentia Real Bussiness Solution S.L.
 * 	Tlf: +34 91 675 33 06, +34 655 99 11 72
 * 	Fax: +34 91 656 65 04
 * 	info@autentia.com
 */

package com.autentia.intra.util;

import java.io.File;

public class FileUtil {
    /**
     * Get the filename (name+extension) part of the given path
     *
     * @param path the path to the file
     * @return the filename (name+extension) part of the given path
     */
    public static String getFileName(String path) {
        String ret = (path == null) ? "" : path;

        int i = ret.lastIndexOf("\\");
        if (i == -1) {
            i = ret.lastIndexOf("/");
        }

        return ret.substring(i + 1);
    }

    /**
     * Delete a full directory tree including files and subdirectories inside it.
     *
     * @param path the path to delete
     * @return true if everything was deleted, false otherwise
     */
    public static boolean deleteTree(File path) {
        File[] files = path.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteTree(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }

        return path.delete();
    }
}