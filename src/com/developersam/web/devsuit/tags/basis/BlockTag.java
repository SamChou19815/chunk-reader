package com.developersam.web.devsuit.tags.basis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * It is an abstract subclass of CustomTag.
 * This type of custom tag prints html blocks, usually with a body content.
 * A block tag has a close connection with a normal html tag.
 * Block tag provides comprehensive supports for nested block tag.
 */
public abstract class BlockTag extends CustomTag {

    // Variables that supports nested custom tags
    private List<CustomTag> childrenTag = new ArrayList<>();

    // Variables that supports common tag attributes and info
    private String tagName = "div"; // default value of tag name, usually does not need to be changed
    private String idString = "";
    private String preDefinedClasses; // Every direct subclass of block tag sets up this in constructor.
    private String customClassesString = "";
    private String onClickString = "";
    private String additionalAttributeString = "";
    private String bodyContent;

    /**
     * Link a parent block tag to itself.
     * It can therefore use its parent's JspWriter, which is the only available one, to doTag.
     * @param parentTag parent tag that is a BlockTag
     */
    @Override
    public void setParent(JspTag parentTag) {
        super.setParent(parentTag);
    }

    /**
     * Obtain a parent tag.
     * Although this method is public, it is intended to be used by this superclass only to support nested block tag.
     * @return parent block tag
     */
    @Override
    public BlockTag getParent() {
        try {
            return (BlockTag) super.getParent();
        }catch (ClassCastException e) {
            return null; // only BlockTag is considered as valid parent
        }
    }

    /**
     * Add children to itself.
     * The children will appear in body content
     * It can therefore print content of its children in body content.
     * It recursively prints all the children content by Java polymorphism design.
     * @param child child tag
     */
    public void addChildrenTag(BlockTag child) {
        child.setParent(this);
        childrenTag.add(child);
    }

    /**
     * Set the tag name for the tag. If subclasses are not using the default value of div,
     * they should change it via this method in constructor.
     * This method is made protected to only let all subclass call it.
     * @param tagName a new tag name other than div.
     */
    protected void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Set id of the tag. The id can therefore be used by Javascript code to perform further actions.
     * This method is made public to make all custom tags support declaring id in web pages.
     * @param id id of the html tag
     */
    public void setId(String id) {
        idString = " id=\"" + id + "\"";
    }

    /**
     * Set the pre-defined classes for the tag. Every direct subclass should call this method.
     * This method is made protected to only let all subclass call it.
     * @param preDefinedClasses pre-defined css classes for the item.
     */
    protected void setPreDefinedClasses(String preDefinedClasses) {
        this.preDefinedClasses = preDefinedClasses;
    }

    /**
     * Set additional css classes.
     * It enables the extension of the same block tag to satisfy different needs.
     * It is recommended to use only in jsp, not in nested block tag handler to separate design with structure.
     * This method is made public to make all custom tags support adding custom classes in web pages.
     * @param customClasses additional css classes.
     */
    public void setCustomClasses(String customClasses) {
        customClassesString = " " + customClasses;
    }

    /**
     * Set onclick of the tag.
     * It enables the interaction between custom tag library and Javascript.
     * This method is made public to make all custom tags support specifying actions when clicked.
     * @param onClick String form of Javascript code
     */
    public void setOnClick(String onClick) {
        onClickString = " onclick=\"" + onClick + "\"";;
    }

    /**
     * Add additional attributes string for the tag.
     * This method is made public to make all custom tags support additional attributes in web pages.
     * @param additionalAttributeString additional attributes.
     */
    public void addAdditionalAttributeString(String additionalAttributeString) {
        this.additionalAttributeString += additionalAttributeString;
    }

    /**
     * Set the body content of the tag.
     * It is used only when the body content is dynamically calculated by JSP EL.
     * This method is made public to make all custom tags support using custom body content.
     * @param bodyContent body content in string.
     */
    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    /**
     * Initialize additional variables after construction and before do tag.
     * This method is called when the block tag has additional attributes beyond standard block tag.
     */
    protected void initialize() {}

    /**
     * Print content to jsp.
     * If the tag has a parent tag, it uses its parent's JspWriter.
     * @param content content in html.
     * @throws JspException jsp exception.
     * @throws IOException io exception.
     */
    protected void printContent(String content) throws JspException, IOException {
        if (getParent() == null) {
            super.printContent(content);
        }else{
            getParent().printContent(content);
        }
    }

    /**
     * Automatically print body content under different conditions.
     * If the tag has children, it means that engineers decide to set its children's content as body content,
     *  The program just prints the content of each child.
     * If the tag has no children, it should print more static form of body content.
     * Case 1: Body content is written between block tags
     *  The program fetches the content and prints it.
     * Case 2: Body content is dynamically calculated by JSP EL and passed into custom tags.
     *  The program prints the calculated body content.
     * @throws JspException jsp exception.
     * @throws IOException io exception.
     */
    protected void printBodyContent() throws JspException, IOException {
        if (childrenTag.size() != 0) {
            for (CustomTag child: childrenTag) {
                child.doTag();
            }
        }
        else {
            if (bodyContent == null) {
                try {
                    StringWriter sw = new StringWriter();
                    getJspBody().invoke(sw);
                    printContent(sw.toString()); // content
                } catch (NullPointerException e) {
                    // do nothing
                }
            }else {
                printContent(bodyContent);
            }
        }
    }

    /**
     * It prints the start of the wrapper around the body content.
     * The default implementation prints the outer most wrapper start.
     * Subclass must override this method if it has more than one wrapper.
     * @throws JspException jsp exception.
     * @throws IOException io exception.
     */
    protected void printWrapperStart() throws JspException, IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tagName).append(idString);
        String classesString = " class=\"" + preDefinedClasses + customClassesString + "\"";
        sb.append(classesString).append(onClickString).append(additionalAttributeString).append(">");
        printContent(sb.toString());
    }

    /**
     * It prints the end of the wrapper around the body content.
     * The default implementation prints the outer most wrapper end.
     * Subclass must override this method if it has more than one wrapper.
     * @throws JspException jsp exception.
     * @throws IOException io exception.
     */
    protected void printWrapperEnd() throws JspException, IOException {
        printContent("</" + tagName + ">");
    }

    /**
     * It prints the content of the card.
     * The default implementation works for most custom cards with a reasonable body content.
     * Subclass generally does not override this method.
     * Instead they should override its constituents to have a same effect but with a cleaner structure.
     * It must be ensured that doTag only outputs content through printContent,
     * so that nested custom tags can be well supported.
     * @throws JspException jsp exception.
     * @throws IOException io exception.
     */
    @Override
    public void doTag() throws JspException, IOException {
        initialize();
        printWrapperStart();
        printBodyContent();
        printWrapperEnd();
    }
}
