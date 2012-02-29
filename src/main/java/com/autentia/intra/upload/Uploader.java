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
package com.autentia.intra.upload;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import java.io.IOException;

/**
 * Interface for uploading files. An Uploader is responsible for storing uploaded files
 * in its correct place.
 *
 * @author ivan
 */
public interface Uploader {
    /**
     * Store an uploaded file under the object with specified id. If the file exists
     * it is overwritten.
     *
     * @param id   id of object (can be null if file is not associated to object)
     * @param file uploaded file
     */
    public void store(String id, UploadedFile file) throws IOException;

    /**
     * Replace an existing uploaded file under the object with specified id
     *
     * @param id      id of object (can be null if file is not associated to object)
     * @param oldFile old uploaded file (if it is null, the method works as store)
     * @param newFile uploaded file
     */
    public void replace(String id, String oldFile, UploadedFile newFile) throws IOException;

    /**
     * Version an existing file. The old file is renamed with a leading version number.
     * The new file name is not taken into account, the old name is always used.
     *
     * @param id      id of object
     * @param oldFile old file name (if it is null, the method works as store)
     * @param newFile new uploaded file
     */
    public void version(String id, String oldFile, UploadedFile newFile) throws IOException;

    /**
     * Check if a file exists under the specified object
     *
     * @param id   id of object (can be null if file is not associated to object)
     * @param file file to check for
     * @return true if the file exists
     */
    public boolean exists(String id, String file);

    /**
     * Delete an existing file.
     *
     * @param id   id of object (can be null if file is not associated to object)
     * @param file file name
     * @return false it the file does not exist, true if the file existed and could be deleted
     * @throws IOException if the file exists and cannot be deleted
     */
    public boolean delete(String id, String file) throws IOException;
}
