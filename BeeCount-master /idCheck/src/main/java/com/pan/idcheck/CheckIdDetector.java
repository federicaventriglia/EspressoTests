package com.pan.idcheck;

import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.LayoutDetector;
import com.android.tools.lint.detector.api.LintFix;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;

import static com.android.SdkConstants.ANDROID_URI;
import static com.android.SdkConstants.ATTR_CONTENT_DESCRIPTION;
import static com.android.SdkConstants.ATTR_ID;

import org.w3c.dom.Element;

import java.io.File;
import java.util.Collection;
import java.util.Random;

public class CheckIdDetector extends LayoutDetector {

    private static final Random random = new Random();
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final Implementation IMPLEMENTATION = new Implementation(
            CheckIdDetector.class,
            Scope.RESOURCE_FILE_SCOPE
    );

    // Issues discovered by this detector, describing the problem and pointing to the implementation

    public static final Issue IDISSUE = Issue.create(
            // ID: used in @SuppressLint warnings
            "com.pan.MissingIdAttribute",

            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            "id attribute is missing",

            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            "ID attribute is the most used and important attribute to uniquely " +
                    "identify each layout element!",

            Category.CORRECTNESS,
            6,
            Severity.WARNING,
            IMPLEMENTATION
            );

    public static final Issue CONTDESCISSUE = Issue.create(
            // ID: used in @SuppressLint warnings
            "com.pan.MissingContentDescriptionAttribute",

            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            "content description attribute is missing",

            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            "contentDescription attribute may be used as an alternative to id " +
                    "attribute for element matching",

            Category.CORRECTNESS,
            5,
            Severity.WARNING,
            IMPLEMENTATION
            );

    public static final Issue BOTHISSUE = Issue.create(
            // ID: used in @SuppressLint warnings
            "com.pan.MissingIdAndContentDescriptionAttributes",

            // Title -- shown in the IDE's preference dialog, as category headers in the
            // Analysis results window, etc
            "both id and content description attributes are missing",

            // Full explanation of the issue; you can use some markdown markup such as
            // `monospace`, *italic*, and **bold**.
            "It's important to uniquely identify each layout element with one of these " +
                    "significant attributes!",

            Category.CORRECTNESS,
            6,
            Severity.WARNING,
            IMPLEMENTATION
            );

    public CheckIdDetector() {
    }

    @Override
    public Collection<String> getApplicableElements() {
        return ALL;
    }

    @Override
    public void visitElement(@NonNull XmlContext context, @NonNull Element element) {

        if (!element.getTagName().equals("include")) {

            //filename
            File file = context.file;
            String fileName = file.getName();
            if (fileName.indexOf(".") > 0)
                fileName = fileName.substring(0, fileName.lastIndexOf("."));

            //element tag name
            String tag = element.getTagName();
            if (tag.startsWith("android"))
                tag = tag.substring(tag.lastIndexOf(".") + 1, tag.length());

            //id generation
            String id = createId(fileName, tag);

            //missing id attribute
            if(!element.hasAttributeNS(ANDROID_URI, ATTR_ID)) {

                //Fix : id setting
                LintFix fixId = fix()
                        .set(ANDROID_URI, ATTR_ID, "@+id/" + id)
                        .build();

                //simultaneous lack of content description attribute
                if(!element.hasAttributeNS(ANDROID_URI,ATTR_CONTENT_DESCRIPTION)){

                    //Fix: content description setting
                    LintFix fixContDescToNewId = fix()
                            .set(ANDROID_URI,ATTR_CONTENT_DESCRIPTION,id)
                            .build();

                    //Report: Fix Group (alternative type)
                    LintFix fixes = fix().group(fixId, fixContDescToNewId);
                    context.report(BOTHISSUE,
                            element,
                            context.getLocation(element),
                            "Missing both id and content description attributes",
                            fixes);

                } else {

                    //Report: Fix only id report
                    context.report(IDISSUE,
                            element,
                            context.getLocation(element),
                            "Missing id attribute",
                            fixId);
                }

                //missing only content description attribute
            } else if(!element.hasAttributeNS(ANDROID_URI,ATTR_CONTENT_DESCRIPTION)){

                String elementId = element.getAttributeNS(ANDROID_URI, ATTR_ID);
                String contentDesc = elementId.substring(elementId.indexOf("/")+1);

                //Fix : content description setting
                LintFix fixContentDesc = fix()
                        .set(ANDROID_URI, ATTR_CONTENT_DESCRIPTION, contentDesc)
                        .build();
                context.report(CONTDESCISSUE,
                        element,
                        context.getLocation(element),
                        "Missing content description attribute",
                        fixContentDesc);

            }

        }

    }

    public String createId(String filename, String tag){

        //initial id
        String id = filename + "_" + tag + "_";

        //length of the distinctive suffix to be generated
        int length = 4;

        //suffix generation
        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            token.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return id + token.toString();
    }
}



