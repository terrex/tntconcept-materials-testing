<%-- 
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L.  
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
 *
 --%>
<%@page language="java" contentType="text/html; charset=UTF-8" %>


<html>
<head>
    <script type="text/javascript">
        function openMain()
        {
            opts = 'width=' + screen.width + ',height=' + screen.height;
            try {
                var v = window.open('root.jsf', this.target, opts);
                v.focus();
            } catch(e) {
                alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
            }
        }

    </script>
</head>
<body onLoad="openMain();">

Si no se abre ninguna ventana pulse <a href="#" onclick="openMain();">aqui</a>

</body>
</html>