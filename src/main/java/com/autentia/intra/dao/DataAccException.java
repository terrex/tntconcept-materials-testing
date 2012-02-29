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

/*
 * DataAccException.java
 */
package com.autentia.intra.dao;

/**
 * Clase padre de todas las excepciones que se produzcan en la capa de acceso a datos
 *
 * @author <a href="www.autentia.com">AUTENTIA</a>
 */
public class DataAccException extends RuntimeException {
    /**
     * Construye una excepci�n con mensaje
     *
     * @param msg Mensaje adicional de error
     */
    public DataAccException(String msg) {
        super(msg);
    }

    /**
     * Construye una excepci�n con un mensaje y la excepci�n original
     *
     * @param msg Mensaje adicional de error
     * @param e   Excepci�n original
     */
    public DataAccException(String msg, Throwable e) {
        super(msg, e);
    }


}
