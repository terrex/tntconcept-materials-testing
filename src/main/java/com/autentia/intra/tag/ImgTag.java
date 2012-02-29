package com.autentia.intra.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ImgTag extends TagSupport {
    /**
     * Type of object the file is attached to
     */
    private String type;

    /**
     * Id of object the file is attached to
     */
    private int objectId;

    /**
     * File name
     */
    private String file;


    @Override
    public void release() {
        super.release();
        type = null;
        objectId = 0;
        file = null;

    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        JspWriter out = pageContext.getOut();

        try {
            if (file != null && !file.equals("")) {
                out.print("<img src=\"" + request.getContextPath() + "/doc/" + type + "/" + objectId + "/" + file + "\">");
            }
        }
        catch (IOException e) {
            throw new JspException("Error rendering imgTag", e);
        }

        return EVAL_PAGE;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
