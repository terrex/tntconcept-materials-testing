package com.autentia.intra.jsf.schedule.renderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.schedule.HtmlSchedule;
import org.apache.myfaces.custom.schedule.model.ScheduleModel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import java.io.IOException;
import java.io.Serializable;

public class ActivityScheduleRenderer extends Renderer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Log log = LogFactory.getLog(ActivityScheduleRenderer.class);

    // ~ Instance fields
    // --------------------------------------------------------

    private final BitacoreScheduleCompactMonthRenderer monthDelegate = new BitacoreScheduleCompactMonthRenderer();
    private final BitacoreScheduleCompactWeekRenderer weekDelegate = new BitacoreScheduleCompactWeekRenderer();
    private final BitacoreScheduleDetailedDayRenderer dayDelegate = new BitacoreScheduleDetailedDayRenderer();

    // ~ Methods
    // ----------------------------------------------------------------

    /**
     * @see javax.faces.render.Renderer#decode(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public void decode(FacesContext context, UIComponent component) {
        getDelegateRenderer(component).decode(context, component);
    }

    /**
     * @see javax.faces.render.Renderer#encodeBegin(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        getDelegateRenderer(component).encodeBegin(context, component);
    }

    /**
     * @see javax.faces.render.Renderer#encodeChildren(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        getDelegateRenderer(component).encodeChildren(context, component);
    }

    /**
     * @see javax.faces.render.Renderer#encodeEnd(javax.faces.context.FacesContext,
     *      javax.faces.component.UIComponent)
     */
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        getDelegateRenderer(component).encodeEnd(context, component);
    }

    protected Renderer getDelegateRenderer(UIComponent component) {
        HtmlSchedule schedule = (HtmlSchedule) component;

        if ((schedule == null) || (schedule.getModel() == null)) {
            return dayDelegate;
        }

        switch (schedule.getModel().getMode()) {
            case ScheduleModel.WEEK:
                return weekDelegate;

            case ScheduleModel.MONTH:
                return monthDelegate;

            default:
                return dayDelegate;
        }

    }
}
// The End
