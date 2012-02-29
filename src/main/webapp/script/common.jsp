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

        <%@page language="java" contentType="text/javascript; charset=UTF-8"%>

    /* <script> */


        function setLocation(locText, iconsUrl, userUrl)
        {

        }
    // Function to open help system
        function showHelp(page, item)
        {
            try {
                var v = window.open(page, "help", "location=no,menu=no,scrollbars=yes,resizable=yes,top=30,left=30,width=800,height=600");
                v.focus();
            } catch(e) {
                alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
            }
        }

    // Refresh the windows
        function refresh()
        {
            document.location.reload();
        }

    // Print the screen
        function printScreen()
        {
            window.print();
        }


    // Function to open attached files
        function openFile(type, id, file, mime)
        {
            var url = '<%=request.getContextPath()%>/doc/' +
                      type + '/' + id + '/' + file + '?' +
                      'mime=' + escape(mime);

            try {
                var v = window.open(url, "", "left=20,top=20,width=800,height=600," +
                                             "scrollbars=yes,resizable=yes,menubar=yes,status=yes,toolbar=yes");
                v.focus();

            } catch(e) {
                alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
            }
        }

    // Function to open attached files
        function openDocumentFile(file)
        {
            var url = '<%=request.getContextPath()%>/doc/document/' + file + "?mime=intraweb/document";

            try {
                var v = window.open(url, "", "left=20,top=20,width=800,height=600," +
                                             "scrollbars=yes,resizable=yes,menubar=yes,status=yes,toolbar=yes");
                v.focus();
            } catch(e) {
                alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
            }
        }

    // Function to open reports
        function openReport(report)
        {
            var url = '<%=request.getContextPath()%>/report/' + report + "?";
            var args = openReport.arguments;

            for (i = 1; i < args.length; i += 2) {
                if (i > 1) url += "&";
                url += args[i];
                url += "=";
                url += args[i + 1];
            }
            try {
                var v = window.open(url, "report", "left=20,top=20,width=800,height=600," +
                                                   "scrollbars=yes,resizable=yes,menubar=yes,status=yes,toolbar=yes");
                v.focus();
            } catch(e) {
                alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
            }
        }


        function openReportParameters(report, parameters, selectMany)
        {
            var url = '<%=request.getContextPath()%>/report/' + report;
            var j = 0;
            var reportName = 'report';
            // argumentos
            var args = openReportParameters.arguments;
            // argumento del tipo selectMany
            if (args[2].length != 0)
            {
                var selectManyItems = args[2].split("=");
                // Items del selectMany
                var param = selectManyItems[1].substring(1, selectManyItems[1].length - 2);
                // cada elemento de selectMany
                var tmp = param.split(",");
                // componemos la url parcial
                url += parameters;
                url += selectManyItems[0];
                url += '=';
                url2 = url;
                for (i = 0; i < tmp.length; i++)
                {
                    j++;
                    // quitamos los posibles espacios
                    url += tmp[i].replace(" ", "");
                    try {
                        var v = window.open(url, reportName.concat(reportName, j), "left=20,top=20,width=800,height=600," +
                                                                                   "scrollbars=yes,resizable=yes,menubar=yes,status=yes,toolbar=yes");
                        v.focus();
                    } catch(e) {
                        alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
                    }
                    // limpiamos la url parcial
                    url = url2;
                }
            } else {
                url += parameters;
                try {
                    var v = window.open(url, reportName, "left=20,top=20,width=800,height=600," +
                                                         "scrollbars=yes,resizable=yes,menubar=yes,status=yes,toolbar=yes");
                    v.focus();
                } catch(e) {
                    alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
                }

            }
        }


    // Function to enable/disable a control
        function setEnabled(id, enabled)
        {
            try {
                document.getElementById(id).disabled = !enabled;
            } catch (e) {
                document.all[id].disabled = !enabled;
            }
        }

    // Function to check/uncheck a checkbox
        function setChecked(id, checked)
        {
            try {
                document.getElementById(id).checked = checked;
            } catch (e) {
                document.all[id].checked = checked;
            }
        }

    // Function to enable/disable a control by name
        function setEnabledByName(name, enabled)
        {
            var i;
            var item;
            try {
                var lista = document.getElementsByName(name);
                for (item in lista) {
                    item.disabled = !enabled;
                }
            } catch(e) {
                for (i = 0; i < document.all.length; i++) {
                    item = document.all[i];
                    if (item.name == name) {
                        item.disabled = !enabled;
                    }
                }
            }
        }

        function askSave(id, message) {
            if (id != "") {
                if (!confirm(message)) {
                    return false;
                }
            }
            return true;
        }

        function redirectToBugs() {
            try {
                var v = window.open("http://sourceforge.net/tracker/?atid=933040&group_id=190357&func=browse");
                v.focus();
            } catch(e) {
                alert("Se ha impedido abrir la ventana por algún programa de bloqueo de ventanas emergentes...");
            }
        }

    /* </script> */














