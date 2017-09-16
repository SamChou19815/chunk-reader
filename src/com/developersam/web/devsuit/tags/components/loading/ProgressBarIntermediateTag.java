package com.developersam.web.devsuit.tags.components.loading;

import com.developersam.web.devsuit.tags.basis.BlockTag;

/**
 * A material design progress bar (intermediate).
 */
public class ProgressBarIntermediateTag extends BlockTag {

    public ProgressBarIntermediateTag() {
        setPreDefinedClasses("mdl-progress mdl-js-progress mdl-progress__indeterminate");
        addAdditionalAttributeString(" style=\"display:none\"");
    }

    /**
     * A progress bar has no content
     */
    @Override
    protected void printBodyContent() {}


}