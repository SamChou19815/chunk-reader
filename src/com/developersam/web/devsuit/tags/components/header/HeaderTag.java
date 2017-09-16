package com.developersam.web.devsuit.tags.components.header;

import com.developersam.web.devsuit.tags.basis.TemplateTag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * This tag prints the header part of the web page, with customizable title and selected module id.
 */
public class HeaderTag extends TemplateTag {

    private String title;
    private int selected;

    /**
     * Set the title that appears on the action bar.
     * @param title title that can be dynamically calculated.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the selected module id.
     * The id describes the position of that module in drawer.
     * Currently, the list is:
     *  0: Home
     *  1: Blog
     *  2: Projects
     *  3: Store
     *  4: Scheduler
     *  5: TEN
     *  6: Bots Civ
     *  7: Shield
     * @param selected module id
     */
    public void setSelected(int selected) {
        this.selected = selected;
    }

    /**
     * Print a custom nav button that can replace a drawer.
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    protected void printCustomNavButton() throws JspException, IOException {}

    /**
     * Print header tag in html
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    protected void printHeader() throws JspException, IOException {
        printContent("<header class=\"mdl-layout__header\">");
        printCustomNavButton();
        printContent("<div class=\"mdl-layout__header-row\">");
        printContent("<span class=\"mdl-layout-title\">" + title + "</span>");
        printContent("<div class=\"mdl-layout-spacer\"></div>");
        printContent("</div>");
        printContent("</header>");
    }

    /**
     * Print nav link
     * @param txt the body content of link
     * @param url link
     * @param selected selected module id
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    private void printNavLink(String txt, String url, boolean selected)
            throws JspException, IOException {
        String selectedString = selected ? " is-active" : "";
        printContent("<a href=\"" + url + "\" class=\"mdl-navigation__link" + selectedString + "\">" + txt + "</a>");
    }

    /**
     * Print the drawer that contains the nav
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    private void printDrawer() throws JspException, IOException {
        printContent("<div class=\"mdl-layout__drawer\">");
        printContent("<span class=\"mdl-layout-title\">Chunk reader</span>");
        printContent("<nav class=\"mdl-navigation\">");
        printNavLink("Home", "/", selected == 0);
        printNavLink("Article","/article.jsp",selected==1);
        printNavLink("Upload", "/upload.jsp", selected ==2);
        printNavLink("About","/about.jsp",selected==3);
        //printNavLink("Home", "/", selected == 0);
        //printNavLink("Blog", "/blog", selected == 1);
        //printNavLink("Projects", "/projects", selected == 2);
        //printNavLink("Store", "/store", selected == 3);
        //printNavLink("Scheduler", "/apps/scheduler/", selected == 4);
        //printNavLink("TEN", "/apps/ten/", selected == 5);
        //printNavLink("Bots Civ", "/apps/botsciv/", selected == 6);
        //printNavLink("Shield", "/apps/shield/", selected == 7);
        //printContent("<a href=\"https://github.com/SamChou19815/Developer-Sam-Website\"");
        //printContent(" class=\"mdl-navigation__link\" target=_blank>Open Source</a>");
        printContent("</nav>");
        printContent("</div>");
    }

    @Override
    public void doTag() throws JspException, IOException {
        printHeader();
        printDrawer();
    }
}