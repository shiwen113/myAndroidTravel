package gem.context;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//统计网站访问的人数,将人数放在context作用域中
		//获得ServletContext对象
		ServletContext context=this.getServletContext();
		//变量记住访问人数,先取，再存
		//处理第一次
		int count=0;
		if(context.getAttribute("count")!=null){
		count=(int) context.getAttribute("count");
		}
		//每访问一次，将人数加1，存回context作用域中
		context.setAttribute("count",++count);
		
		//显示人数
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print("访问人数"+count);
		out.flush();
		out.close();
	}

}
