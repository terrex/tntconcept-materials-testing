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

package com.autentia.intra.manager.security.exception;

/**
 * @author ivan
 */
public class SecException extends java.lang.Exception {

    /**
     * Creates a new instance of <code>SecurityException</code> without detail message.
     */
    public SecException() {
    }

    /**
     * Constructs an instance of <code>SecurityException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SecException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>SecurityException</code> with the specified detail message and cause.
     *
     * @param msg   the detail message.
     * @param cause the cause.
     */
    public SecException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
