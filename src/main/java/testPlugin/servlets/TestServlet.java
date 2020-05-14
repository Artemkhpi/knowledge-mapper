package testPlugin.servlets;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.templaterenderer.TemplateRenderer;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.atlassian.sal.api.transaction.*;

import static com.google.common.base.Preconditions.*;

@Scanned
public final class TestServlet extends HttpServlet {

    @ComponentImport
    private final ActiveObjects ao;

    @ComponentImport
    private final TemplateRenderer templateRenderer;

    @Inject
    public TestServlet(ActiveObjects ao, TemplateRenderer templateRenderer) {
        this.ao = checkNotNull(ao);
        this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        final PrintWriter printWriter = resp.getWriter();

        printWriter.write("<html><head>\n" +
                "    <meta name=\"decorator\" content=\"atl.general\"/>\n" +
                "  </head>");
        printWriter.write("<form method=\"post\">");
        printWriter.write("<input type=\"text\" name=\"task\" size=\"25\"/>");
        printWriter.write("  ");
        printWriter.write("<input type=\"submit\" name=\"submit\" value=\"Add\"/>");
        printWriter.write("</form>");

        printWriter.write("<ol>");

        ao.executeInTransaction(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction() {
                for (TestEntity testEntity : ao.find(TestEntity.class))
                {
                    printWriter.printf("<li><%2$s> %s </%2$s></li>", testEntity.getDescription(), testEntity.isComplete() ? "strike" : "strong");
                }

                return null;
            }
        });

        printWriter.write("</ol>");
        printWriter.write("<script language='javascript'>document.forms[0].elements[0].focus();</script>");
        printWriter.write("</html>");

        printWriter.close();
        */

        templateRenderer.render("main.vm", resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String description = req.getParameter("task");

        /*
        ao.executeInTransaction(new TransactionCallback<TestEntity>()
        {
            @Override
            public TestEntity doInTransaction()
            {
                final TestEntity testEntity = ao.create(TestEntity.class);
                testEntity.setDescription(description);
                testEntity.setComplete(false);
                testEntity.save();
                return testEntity;
            }
        });
         */
        resp.sendRedirect(req.getContextPath() + "/plugins/servlet/test/list");
    }
}