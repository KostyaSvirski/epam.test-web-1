package by.svirski.testweb.controller.tag;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.tag.util.TagUtil;

public class MainMenuTag extends TagSupport {

	private static final long serialVersionUID = -6903218681743486184L;
	
	private static final String MY_PAGE = "welcome_page.header.nav.my_page";
	private static final String SIGN_IN = "welcome_page.header.nav.sign_in";
	private static final String CONTACTS = "welcome_page.header.nav.contacts";
	private static final String CAR_BRANDS = "welcome_page.header.nav.car_brands";
	private static final String RENT = "welcome_page.header.nav.rent";
	private static final String AUTOPARK = "welcome_page.header.nav.autopark";
	private static final String ABOUT_US = "welcome_page.header.nav.about_us";

	public MainMenuTag() {
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		JspWriter writter = pageContext.getOut();
		HttpSession session = pageContext.getSession();
		String locale = (String) session.getAttribute(RequestParameters.LANGUAGE);
		ResourceBundle bundle = TagUtil.getBundle(locale);
		try {
			writter.write("<header class=\"header\">");
			writter.write(
					"<a href=\"index.jsp\" <img class=\"logo\" "
					+ "src=\"https://www.freelancejob.ru/upload/139/29eb6b9055a15d9a3aaca113ce12f81b.png\" "
					+ "alt=\"logo\"/> </a>");
			writter.write("<nav>");
			createAboutUsLink(writter, bundle);
			createAutoparkLink(writter, bundle);
			createRentLink(writter, bundle);
			createCarBrandsLink(writter, bundle);
			createContactsLink(writter, bundle);
			if (session.getAttribute(RequestParameters.USER) == null) {
				createSignInLink(writter, bundle);
			} else {
				createMyPageLink(writter, bundle);
			}
			writter.write("</nav>");
			writter.write("</header>");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	private void createMyPageLink(JspWriter writter, ResourceBundle bundle) throws IOException {
		String nameOfButton = bundle.getString(MY_PAGE);
		writter.write("<a class=\"nav\" href=\"my_page.jsp\">" + nameOfButton + "</a>");
	}

	private void createSignInLink(JspWriter writter, ResourceBundle bundle) throws IOException {
		String nameOfButton = bundle.getString(SIGN_IN);
		writter.write("<a class=\"nav\" href=\"sign_in.jsp\">" + nameOfButton + "</a>");
		
	}

	private void createContactsLink(JspWriter writter, ResourceBundle bundle) throws IOException {
		String nameOfButton = bundle.getString(CONTACTS);
		writter.write("<a class=\"nav\" href=\"index.jsp#contacts\">" + nameOfButton + "</a>");
		
	}

	private void createCarBrandsLink(JspWriter writter, ResourceBundle bundle) throws IOException {
		String nameOfButton = bundle.getString(CAR_BRANDS);
		writter.write("<a class=\"nav\" href=\"index.jsp#carBrands\">" + nameOfButton + "</a>");
		
	}

	private void createRentLink(JspWriter writter, ResourceBundle bundle) throws IOException {
		String nameOfButton = bundle.getString(RENT);
		writter.write("<a class=\"nav\" href=\"index.jsp#rent\">" + nameOfButton + "</a>");
		
	}

	private void createAutoparkLink(JspWriter writter, ResourceBundle bundle) throws IOException {
		String nameOfButton = bundle.getString(AUTOPARK);
		writter.write("<a class=\"nav\" href=\"index.jsp#autopark\">" + nameOfButton + "</a>");
		
	}

	private void createAboutUsLink(JspWriter writter, ResourceBundle bundle) throws IOException {
		String nameOfButton = bundle.getString(ABOUT_US);
		writter.write("<a class=\"nav\" href=\"index.jsp#aboutUs\">" + nameOfButton + "</a>");
		
	}

}
