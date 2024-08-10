package max.regist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SHGMasterServlet
 */
@WebServlet("/SHGMasterServlet")
public class SHGMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		SHGBean sb = new SHGBean();
		SHGBean sHGBean = sb.setSHGDetail(request);
		
		SHGMemberBean sHGMemberBean=new SHGMemberBean();
		
		SHGMemberBean mdetail = sHGMemberBean.setMemberBean(request);
		
		//sHGBean one  mdetail many
		//bh_shg_detail, bh_member-- UP-raja11111.substring(0,2)
		
		HttpSession s = request.getSession();
		String ud = (String)s.getAttribute("uid");
		
		SHGMemberDAO sHGMemberDAO=new SHGMemberDAO();
		
		if(sHGMemberDAO.getSHGMemberDetails(ud.substring(0, 2).toLowerCase(), sHGBean, mdetail))
		{ out.println("sucessss ful inserted");
			request.getRequestDispatcher("Home.jsp").include(request, response);
		}
		else
		{
			out.println("un sucessss ful inserted");
			request.getRequestDispatcher("Home.jsp").include(request, response);
		}
		
	}


}
