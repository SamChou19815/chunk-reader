package com.developersam.web.devsuit.tags.components.button;

import com.developersam.web.devsuit.tags.basis.BlockTag;

/**
 * A link that can serve as a button, decorated by material design.
 */
public class LinkButtonTag extends BlockTag {

    public LinkButtonTag() {
        setTagName("a");
        setPreDefinedClasses("mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect");
    }

    private String hrefString;
    private String openInNewTabString;

    /**
     * Set href of link
     * @param href href of link
     */
    public void setHref(String href) {
        hrefString = " href=\"" + href + "\"";;
    }

    /**
     * Set whether the link should be open in new tab.
     * If the button has onclick attribute, it should be set as false.
     * @param openInNewTab whether to open the link in new tab
     */
    public void setOpenInNewTab(boolean openInNewTab) {
        if (openInNewTab) {
            openInNewTabString = " target=_blank";
        }else {
            openInNewTabString = "";
        }
    }

    @Override
    protected void initialize() {
        addAdditionalAttributeString(hrefString + openInNewTabString);
    }
}