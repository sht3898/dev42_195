package com.ssafy.manv.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.manv.entity.GithubMember;
import com.ssafy.manv.entity.GithubUserEmail;
import com.ssafy.manv.entity.Member;
import com.ssafy.manv.response.CommonResponse;
import com.ssafy.manv.response.LoginResponse;
import com.ssafy.manv.response.MemberNumberResult;
import com.ssafy.manv.response.SingleResult;
import com.ssafy.manv.service.GithubMemberService;
import com.ssafy.manv.service.JwtTokenService;
import com.ssafy.manv.service.MailUtil;
import com.ssafy.manv.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@Api(value = "MemberController")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private GithubMemberService githubMemberService;

	@ApiOperation(value = "회원가입", notes = "회원가입")
	@RequestMapping(value = "/addMember", method = RequestMethod.POST)
	public ResponseEntity<CommonResponse> addMember(@RequestBody Member member) throws Exception {
		
		logger.info("================addMember================\t" + new Date());
		logger.info(member.toString());
		
		Member m = memberService.findByEmail(member.getEmail());

		CommonResponse mnr = new CommonResponse();
		mnr.setCode(0);
		mnr.setMsg("succ addMember");
		mnr.setState(CommonResponse.SUCC);

		if (m != null) {
			mnr.setCode(-1);
			mnr.setMsg("fail addMember");
			mnr.setState(CommonResponse.FAIL);
			return new ResponseEntity<>(mnr, HttpStatus.OK);
		}
		memberService.save(member);
		return new ResponseEntity<>(mnr, HttpStatus.OK);
	}

	@ApiOperation(value = "회원정보", notes = "회원정보")
	@RequestMapping(value = "/getMemberByID/{email}", method = RequestMethod.GET)
	public ResponseEntity<Member> getMemberByID(@PathVariable String email) throws Exception {
		
		logger.info("================getMemberByID================\t" + new Date());
		logger.info(email);

		Member m = memberService.findByEmail(email);

		if (m == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(m, HttpStatus.OK);
	}

	
	@ApiOperation(value = "회원탈퇴", notes = "회원탈퇴")
	@RequestMapping(value = "/deleteMember/{email}", method = RequestMethod.GET)
	public ResponseEntity<CommonResponse> deleteMember(@RequestHeader("x-access-token") String accesstoken, @PathVariable String email) throws Exception {
		
		logger.info("================deleteMember================\t" + new Date());
		logger.info(email);
		logger.info(accesstoken);
		if(!jwtTokenService.validateToken(accesstoken) || !jwtTokenService.getUserPk(accesstoken).equals(email) ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Member m = memberService.findByEmail(email);

		CommonResponse mnr = new CommonResponse();
		mnr.setCode(0);
		mnr.setMsg("deleteMember");
		mnr.setState(CommonResponse.SUCC);

		if (m == null) {
			mnr.setCode(-1);
			mnr.setMsg("deleteMember");
			mnr.setState(CommonResponse.FAIL);
			return new ResponseEntity<>(mnr, HttpStatus.OK);
		}

		memberService.delete(m);
		return new ResponseEntity<>(mnr, HttpStatus.OK);
	}

	
	@ApiOperation(value = "이메일 확인", notes = "이메일 확인")
	@RequestMapping(value = "/checkEmail/{email}", method = RequestMethod.GET)
	public ResponseEntity<CommonResponse> checkEmail(@PathVariable String email) throws Exception {
		logger.info("================checkEmail================\t" + new Date());
		logger.info(email);
		
		Member m = memberService.findByEmail(email);
		CommonResponse mnr = new CommonResponse();
		mnr.setCode(0);
		mnr.setMsg("checkEmail");
		mnr.setState(CommonResponse.SUCC);
		if (m == null) {
			mnr.setCode(-1);
			mnr.setMsg("checkEmail");
			mnr.setState(CommonResponse.FAIL);
			return new ResponseEntity<>(mnr, HttpStatus.OK);
		}
		return new ResponseEntity<>(mnr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "로그인", notes = "로그인")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<LoginResponse> login(@RequestBody Member member) throws Exception {
		logger.info("================login================\t" + new Date());
		logger.info(member.toString());
		
		String id = member.getEmail();
		Member m = memberService.findByEmail(id);
		
		LoginResponse mnr = new LoginResponse(0, "login", CommonResponse.SUCC);
		
		if(m == null || !member.getPwd().equals(m.getPwd())) {
			mnr.setCode(-1);
			mnr.setMsg("login");
			mnr.setState(CommonResponse.FAIL);
			return new ResponseEntity<>(mnr, HttpStatus.OK);
		}
		
		String token = jwtTokenService.createToken(id, m.getAuth());
		logger.info(token + " ====== " + new Date());
		mnr.setAccessToken(token);
		return new ResponseEntity<>(mnr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "로그인중 확인", notes = "로그인중 확인")
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public ResponseEntity<CommonResponse> checkLogin(@RequestHeader("x-access-token") String accesstoken) throws Exception {
		logger.info("================checkLogin================\t" + new Date());
		logger.info("token : " + accesstoken);
		
		CommonResponse mnr = new CommonResponse(0, "checkLogin", CommonResponse.SUCC);
		
		boolean chk = jwtTokenService.validateToken(accesstoken);
		
		if(!chk) {
			mnr.setCode(-1);
			mnr.setState("checkLogin");
			mnr.setState(CommonResponse.FAIL);
			return new ResponseEntity<>(mnr, HttpStatus.OK);
		}
		return new ResponseEntity<>(mnr, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "임시비밀번호 발송", notes = "등록된 이메일로 임시 비밀번호 발급")
	@RequestMapping(value = "/sendTmpPwd/{email}", method = RequestMethod.GET)
	public ResponseEntity<MemberNumberResult> sendTmpPwd(@PathVariable String email) throws Exception {
		System.out.println("================sendTmpPwd================\t" + new Date());
		
		Member m = memberService.findByEmail(email);
		
		MemberNumberResult mnr = new MemberNumberResult();
	
		if (m == null) {
			mnr.setNumber(-1);
			mnr.setName("sendTmpPwd");
			mnr.setState("fail");
			return new ResponseEntity<MemberNumberResult>(mnr, HttpStatus.OK);
		}
		String keyCode = MailUtil.createKey();
		
		String subject = "[HELLO!] 비밀번호 찾기 인증코드 안내";
		String msg = "";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color:blue;'>비밀번호 찾기 인증코드입니다.</h3>";
		msg += "<div style='font-size: 130%'>";
		msg += keyCode + "</strong> 를 입력해주세요.</div><br/>";
		
		MailUtil.sendMail(email, subject, msg);
		m.setPwd(keyCode);
		memberService.save(m);
		//jpa에서 save는 save, update이다. 값이 존재하면 update
		
		mnr.setNumber(0);
		mnr.setName("sendTmpPwd");
		mnr.setState("succ");
		
		return new ResponseEntity<MemberNumberResult>(mnr, HttpStatus.OK);
	}
	
	@ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
    @PostMapping(value = "/signin/github")
    public LoginResponse signinByProvider(@ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {

		//String email  = githubMemberService.getGithubUserPrivateEmail(accessToken).getEmail();
        GithubMember githubMember = githubMemberService.getGithubUser(accessToken);
        String email = githubMember.getLogin();//github로그인시 login이 member.email(pk)가 된다.
		Member member = memberService.findByEmail(email);
        if(member == null) {
        	return new LoginResponse(1, "social login fail", "fail");
        }
        
        //member 에 있는 token -> accessToken으로 업데이트 해야된다.
        member.setToken(accessToken);
        memberService.save(member);
        
        LoginResponse res = new LoginResponse(0, "social login success", CommonResponse.SUCC);
        res.setAccessToken(jwtTokenService.createToken(member.getEmail(), member.getAuth()));
        return res;
    }

    @ApiOperation(value = "소셜 계정 가입", notes = "소셜 계정 회원가입을 한다.")
    @PostMapping(value = "/signup/github")
    public CommonResponse signupProvider(@ApiParam(value = "소셜 access_token가 포함된 member", required = true) @RequestBody Member member) {
    	logger.info("소셜 가입 - " + member.getToken());
        GithubMember githubMember = githubMemberService.getGithubUser(member.getToken());
        //GithubUserEmail githubUserEmail = githubMemberService.getGithubUserPrivateEmail(member.getToken());
        logger.info("소설 가입 - " + githubMember.toString());// " , " + githubUserEmail.getEmail());
        Member findMember = memberService.findByEmail(githubMember.getLogin());
        if (findMember != null)
            return new CommonResponse(1, "이미 회원가입이 되어있습니다.", CommonResponse.FAIL);
        //Member newMember = githubMemberService.getMemberByGithubMember(githubMember, githubUserEmail);
        member.setEmail(githubMember.getLogin());
        member.setGithub(githubMember.getLogin());
        
        memberService.save(member);
        return new CommonResponse(0, "social signup success", CommonResponse.SUCC);
    }
	
}
