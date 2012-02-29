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

/**
 *
 */
package com.autentia.intra.upload.impl;

import com.autentia.intra.upload.Uploader;
import com.autentia.intra.util.ConfigurationUtil;
import com.autentia.intra.util.FileUtil;
import org.apache.commons.lang.NotImplementedException;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import java.io.*;

/**
 * Intraweb default uploader: places files in the path described in configuration file.
 *
 * @author ivan
 */
public class DefaultUploader implements Uploader {

    /**
     * path where files must be stored
     */
    private String path;

    /**
     * Constructor
     *
     * @param object object the file is associated to
     */
    public DefaultUploader(String object) {
        path = ConfigurationUtil.getDefault().getUploadPath() + object + File.separator;
    }

    /** */
    public void store(String id, UploadedFile file) throws IOException {
        byte[] buffer = new byte[65536];
        int nr;

        InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream(getFilePath(id) + FileUtil.getFileName(file.getName()));

        while ((nr = in.read(buffer)) != -1) {
            out.write(buffer, 0, nr);
        }

        in.close();
        out.close();
    }

    /** */
    public void replace(String id, String oldFile, UploadedFile newFile) throws IOException {
        if (oldFile != null) {
            delete(id, oldFile);
        }
        store(id, newFile);
    }

    /** */
    public boolean exists(String id, String file) {
        return new File(getFilePath(id) + file).exists();
    }

    /** */
    public boolean delete(String id, String file) throws IOException {
        String fullPath = getFilePath(id) + file;
        boolean exists = exists(id, file);
        if (!exists) {
            return false;
        } else {
            boolean ok = new File(fullPath).delete();
            if (!ok) {
                throw new IOException("File could not be deleted: " + fullPath);
            } else {
                return true;
            }
        }
    }

    /** */
    public void version(String id, String oldFile, UploadedFile newFile) throws IOException {
        // TODO: implement this method
        throw new NotImplementedException("DefaultUploader: version method not yet implemented");
    }

    /**
     * Get file path and create directories as necessary.
     *
     * @return the file path (directory where it should be stored
     */
    private String getFilePath(String id) {
        String filePath = (id == null) ? (path) : (path + id + File.separator);
        new File(filePath).mkdirs();
        return filePath;
    }
}
