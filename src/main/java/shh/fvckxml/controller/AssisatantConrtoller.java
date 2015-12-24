package shh.fvckxml.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shh.fvckxml.util.verifycode.BaseVerifyCode;
import shh.fvckxml.util.verifycode.IVerifyCode.ImageAndCode;

/**
 * 辅助控制器
 */
@Controller
public class AssisatantConrtoller {
	
	@RequestMapping("/verify_code")
	public void getVerifycode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		// 表明生成的响应是图片
		resp.setContentType("image/jpeg");
		ImageAndCode imageAndCode = BaseVerifyCode.getInstance().getImageAndCode();
		HttpSession session = req.getSession();
		session.setAttribute("code", imageAndCode.code);
		ImageIO.write(imageAndCode.image, "JPEG", resp.getOutputStream());
	}
}
