/*
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
 */

package com.autentia.intra.bean;

import com.autentia.intra.businessobject.*;
import com.autentia.intra.manager.security.Permission;
import com.autentia.intra.manager.security.Principal;
import com.autentia.intra.util.SpringUtils;
import org.acegisecurity.GrantedAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Dynamic Menu definition bean
 *
 * @author German
 */
public class DynMenuBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Log system
     */
    private static final Log log = LogFactory.getLog(DynMenuBean.class);

    /**
     * Language resources
     */
    private static final ResourceBundle msg = ResourceBundle
            .getBundle("com.autentia.intra.resources.messages");
    /**
     * List of NavigationItems
     */
    private List<NavigationMenuItem> navItems = null;

    /**
     * Constructor
     */
    public DynMenuBean() {

    }

    public List<NavigationMenuItem> getNavItems() {
        return generateMenu();
    }

    /**
     * Generates a list of navigation items, representating entries of the dynamic menu bar
     *
     * @return
     */
    private List<NavigationMenuItem> generateMenu() {

        if (navItems == null) {

            if (log.isDebugEnabled()) {
                log.debug("generating dynamic menu");
            }

            Principal creds = null;
            boolean isAnonymous = true;

            try {
                creds = SpringUtils.getPrincipal();
                isAnonymous = false;
            } catch (ClassCastException e) {
                // veamos si es un usuario anonimo
                if ("anonymousUser".equals(SpringUtils.getPrincipalAsObject())) {
                    isAnonymous = true;
                } else {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            navItems = new ArrayList<NavigationMenuItem>();

            NavigationMenuItem item = null;

            if (!isAnonymous) {
                // menu items for NON anonymous users

                if (log.isDebugEnabled()) {
                    log.debug("user is NOT anonymous");
                }

                // file menu
                if ((item = createMenuItem(creds, null, "file", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Setting.class), "settings", "/img/configure.png")));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(User.class), "#{dynMenuBean.exit}", "/img/exit.png")));

                    addItem2Items(navItems, item);
                }

                // administration menu
                if ((item = createMenuItem(creds, null, "admin", null)) != null) {


                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(User.class), "users",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(UserCategory.class),
                            "userCategorys", null)));
                    addItem2Item(item, (createMenuItem(creds, null, "changePassword", "/img/key.png")));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Department.class),
                            "departments", null)));

                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(WorkingAgreement.class),
                            "workingAgreements", null)));


                    addItem2Items(navItems, item);
                }

                // master tables menu
                if ((item = createMenuItem(creds, null, "masterTables", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(AccountEntryType.class),
                            "accountEntryTypes", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(OrganizationType.class),
                            "organizationTypes", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(InteractionType.class),
                            "interactionTypes", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission
                            .Entity_Menu(OrganizationISOCategory.class),
                            "organizationISOCategorys", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(ContractType.class),
                            "contractTypes", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Magazine.class),
                            "magazines", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(OfferRejectReason.class),
                            "offerRejectReasons", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Ensayo.class),
                            "searchEnsayo", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Pauta.class),
                            "searchPauta", null)));

                    if (creds.hasAuthority(Permission.Entity_Menu(Pauta.class))
                            && creds.hasAuthority(Permission.Entity_Menu(Ensayo.class))) {
                        String text = "menu.actualizarPrecios";
                        try {
                            text = msg.getString(text);
                        } catch (MissingResourceException e) {
                            text = "MISSING : " + text + " : MISSING";
                        }
                        addItem2Item(item, new NavigationMenuItem(text, "actualizarPrecios"));
                    }

                    NavigationMenuItem[] arr = item.getNavigationMenuItems();

                    addItem2Items(navItems, item);
                }

                // billing
                if ((item = createMenuItem(creds, null, "billing", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Bill.class), "searchBill",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(AccountEntryType.class),
                            "accounts", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(AccountEntry.class),
                            "accountEntrys", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission
                            .Entity_Menu(PeriodicalAccountEntry.class), "periodicalAccountEntrys",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_NOF, "nof", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(FinancialRatio.class),
                            "financialRatios", null)));
                    addItem2Items(navItems, item);
                }

                // contacs
                if ((item = createMenuItem(creds, null, "contacts", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Organization.class),
                            "organizations", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Interaction.class),
                            "interactions", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Contact.class),
                            "searchContact", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Offer.class), "searchOffer",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Project.class),
                            "searchProject", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Albaran.class), "searchAlbaran",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(NotaSalida.class), "notaSalidas",
                            null)));
                    addItem2Items(navItems, item);
                }

                // quality
                if ((item = createMenuItem(creds, null, "quality", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_ListQualityDocuments,
                            "qualityDocuments", null)));
                    addItem2Items(navItems, item);
                }

                // bulletin
                if ((item = createMenuItem(creds, null, "bulletin", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(BulletinBoard.class),
                            "bulletinBoards", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(CompanyState.class),
                            "companyStates", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission
                            .Entity_Menu(BulletinBoardCategory.class), "bulletinBoardCategorys",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Idea.class), "ideas",
                            null)));
                    addItem2Items(navItems, item);
                }

                // activity
                if ((item = createMenuItem(creds, null, "activity", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Activity.class),
                            "activitys", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Objective.class),
                            "objectives", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_GlobalHoursReport, "globalHoursReport", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Occupation.class),
                            "availabilitys", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Occupation.class),
                            "occupations", null)));
                    addItem2Items(navItems, item);
                }

                // reports tables menu
                if ((item = createMenuItem(creds, null, "reports", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_GeneralReports,
                            "generalReports", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_BitacoreReports,
                            "bitacoreReports", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_BillReports, "billReports",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_ProjectReports,
                            "projectReports", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_InteractionReports,
                            "interactionReports", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_OrganizationReports,
                            "organizationReports", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_OfferReports, "offerReports",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_PersonalReports,
                            "personalReports", null)));
                    addItem2Items(navItems, item);
                }

                // publish
                if ((item = createMenuItem(creds, null, "publish", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Tutorial.class),
                            "tutorials", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Publication.class),
                            "publications", null)));
                    addItem2Items(navItems, item);
                }

                // holiday
                if ((item = createMenuItem(creds, null, "holiday", null)) != null) {


                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Holiday.class),
                            "holidays", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(RequestHoliday.class),
                            "requestHolidays", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(AdminHoliday.class),
                            "adminHolidays", null)));

                    addItem2Item(item, (createMenuItem(creds, Permission.Action_UserHolidaysState, "userHolidayState", null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Action_MyHolidays, "#{userHolidaysStateBean.myHolidays}", null)));

                    addItem2Items(navItems, item);
                }

                // utils
                if ((item = createMenuItem(creds, null, "utils", null)) != null) {
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Book.class), "books",
                            null)));
                    addItem2Item(item, (createMenuItem(creds, Permission.Entity_Menu(Inventary.class),
                            "inventarys", null)));
                    addItem2Items(navItems, item);
                }

            } else {
                // menu items for anonymous users
                if (log.isDebugEnabled()) {
                    log.debug("user is NOT anonymous");
                }
            }

        }

        return navItems;
    }

    private void addItem2Items(List<NavigationMenuItem> items, NavigationMenuItem item) {
        if (item == null || item.getNavigationMenuItems() == null || item.getNavigationMenuItems().length <= 0) {
            return;
        }

        items.add(item);
    }

    private void addItem2Item(NavigationMenuItem padre, NavigationMenuItem hijo) {

        if (hijo != null)
            padre.add(hijo);


    }

    /**
     * Generates a navigation menu item. This item could be nested into others navigation menu items
     *
     * @param creds
     * @param neededRole
     * @param cmd
     * @param iconUrl
     * @return
     */
    private NavigationMenuItem createMenuItem(Principal creds, GrantedAuthority neededRole,
                                              String cmd, String iconUrl) {

        NavigationMenuItem item = null;

        if (neededRole == null || creds.hasAuthority(neededRole)) {
            String text = cmd;
            if (text.indexOf("#{") >= 0) {
                text = text.substring(text.indexOf('.') + 1, text.indexOf('}'));
            }
            try {
                text = msg.getString("menu." + text);
            } catch (MissingResourceException e) {
                text = "MISSING : " + text + " : MISSING";
            }

            if (iconUrl == null) {
                item = new NavigationMenuItem(text, cmd);
            } else {
                item = new NavigationMenuItem(text, cmd, iconUrl, false);
            }

        }

        return item;
    }

    //XXX llamar a este metodo para limpiar la sesion.. (desde donde???)
    /**
     * Clear admin beans from session
     * private void clearSession() {
     * // Remove admin beans from session
     * FacesUtils.removeFromSession("userBean");
     * FacesUtils.removeFromSession("userCategoryBean");
     * FacesUtils.removeFromSession("inventaryBean");
     * FacesUtils.removeFromSession("projectBean");
     * FacesUtils.removeFromSession("ideaBean");
     * FacesUtils.removeFromSession("changePasswordBean");
     * FacesUtils.removeFromSession("userBean");
     * FacesUtils.removeFromSession("userCategoryBean");
     * FacesUtils.removeFromSession("inventaryBean");
     * FacesUtils.removeFromSession("projectBean");
     * FacesUtils.removeFromSession("projectRoleBean");
     * FacesUtils.removeFromSession("ideaBean");
     * FacesUtils.removeFromSession("changePasswordBean");
     * FacesUtils.removeFromSession("departmentBean");
     * FacesUtils.removeFromSession("contractTypeBean");
     * <p/>
     * // Remove biling beans from session
     * FacesUtils.removeFromSession("billBean");
     * FacesUtils.removeFromSession("accountBean");
     * FacesUtils.removeFromSession("accountEntryTypeBean");
     * FacesUtils.removeFromSession("accountEntryBean");
     * FacesUtils.removeFromSession("periodicalAccountEntryBean");
     * FacesUtils.removeFromSession("nofBean");
     * FacesUtils.removeFromSession("financialRatioBean");
     * <p/>
     * // Remove contacts beans from session
     * FacesUtils.removeFromSession("organizationBean");
     * FacesUtils.removeFromSession("contactBean");
     * FacesUtils.removeFromSession("interactionBean");
     * FacesUtils.removeFromSession("interactionTypeBean");
     * FacesUtils.removeFromSession("organizationTypeBean");
     * FacesUtils.removeFromSession("organizationISOCategoryBean");
     * FacesUtils.removeFromSession("offerRejectReasonBean");
     * FacesUtils.removeFromSession("offerBean");
     * <p/>
     * // Remove quality beans from session
     * FacesUtils.removeFromSession("qualityDocumentBean");
     * <p/>
     * // Remove bulletin beans from session
     * FacesUtils.removeFromSession("bulletinBoardBean");
     * FacesUtils.removeFromSession("companypathBean");
     * FacesUtils.removeFromSession("bulletinBoardCategoryBean");
     * <p/>
     * // Remove activity beans from session
     * FacesUtils.removeFromSession("activityBean");
     * FacesUtils.removeFromSession("objectiveBean");
     * <p/>
     * // Remove activity beans from session
     * FacesUtils.removeFromSession("activityReportBean");
     * FacesUtils.removeFromSession("generalReportBean");
     * FacesUtils.removeFromSession("billReportBean");
     * FacesUtils.removeFromSession("interactionReportBean");
     * FacesUtils.removeFromSession("projectReportBean");
     * FacesUtils.removeFromSession("organizationReportBean");
     * FacesUtils.removeFromSession("personalReportBean");
     * FacesUtils.removeFromSession("offerReportBean");
     * <p/>
     * // Remove tutorial beans from session
     * FacesUtils.removeFromSession("tutorialBean");
     * FacesUtils.removeFromSession("publicationBean");
     * FacesUtils.removeFromSession("magazineBean");
     * <p/>
     * // Remove util beans from session
     * FacesUtils.removeFromSession("sendMailBean");
     * }
     */


    public String exit() {
        HttpSession sessionContext = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (sessionContext != null) {
            sessionContext.invalidate();
        }

        return "exit";

    }
}
