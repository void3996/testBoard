package com.ict.controller;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.domain.CategoryVO;
import com.ict.domain.GoodsVO;
import com.ict.domain.GoodsViewVO;
import com.ict.domain.MemberVO;
import com.ict.domain.OrderListVO;
import com.ict.domain.OrderVO;
import com.ict.domain.ReplyListVO;
import com.ict.domain.ReplyVO;
import com.ict.service.AdminService;
import com.ict.utils.UploadFileUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

		@Inject
		AdminService adminService;
		
		@Resource(name="uploadPath")
		private String uploadPath;
	
		// 관리자화면
		@RequestMapping(value = "/index", method = RequestMethod.GET)
		public void getIndex() throws Exception {
			logger.info("get index"); 
		}
		
		// 상품 등록
		@RequestMapping(value = "/goods/register", method = RequestMethod.GET)
		public void getGoodsRegister(Model model) throws Exception {
		 logger.info("get goods register");
		 
		 List<CategoryVO> category = null;
		 category = adminService.category();
		 model.addAttribute("category", JSONArray.fromObject(category));
		}
		
		// 상품 등록
		@RequestMapping(value = "/goods/register", method = RequestMethod.POST)
		public String postGoodsRegister(GoodsVO vo, RedirectAttributes rttr) throws Exception {
			
			System.out.println("==========================");
			System.out.println("register: " + vo);
			
			if (vo.getAttachList() != null) {

				vo.getAttachList().forEach(attach -> System.out.println(attach));

			}
			System.out.println("==========================");
			adminService.register(vo);
			
			rttr.addFlashAttribute("result", vo.getGdsNum());
			return "redirect:/admin/index";
		}
		
		// 상품 목록
		@RequestMapping(value = "/goods/list", method = RequestMethod.GET)
		public void getGoodsList(Model model) throws Exception {
			logger.info("get goods list");
			
			List<GoodsViewVO> list = adminService.goodslist();  // GoodsVO형태의 List형 변수 list 선언
			
			model.addAttribute("list", list);  // 변수 list의 값을 list 세션에 부여
		}
		
		// 상품 조회
		@RequestMapping(value = "/goods/view", method = RequestMethod.GET)
		public void getGoodsview(@RequestParam("n") int gdsNum, Model model) throws Exception {
			logger.info("get goods view");
			
			GoodsViewVO goods = adminService.goodsView(gdsNum);
			model.addAttribute("goods", goods);
		}
		
		// 상품 수정 
		@RequestMapping(value = "/goods/modify", method = RequestMethod.GET)
		public void getGoodsModify(@RequestParam("n") int gdsNum, Model model) throws Exception {
		// @RequestParam("n")으로 인해, URL주소에 있는 n의 값을 가져와 gdsNum에 저장
			
			logger.info("get goods modify");
			
			GoodsViewVO goods = adminService.goodsView(gdsNum);  // GoodsViewVO형태 변수 goods에 상품 정보 저장
			model.addAttribute("goods", goods);
			
			List<CategoryVO> category = null;
			category = adminService.category();
			model.addAttribute("category", JSONArray.fromObject(category));
		}
		
		// 상품 수정
		@RequestMapping(value = "/goods/modify", method = RequestMethod.POST)
		public String postGoodsModify(GoodsVO vo, MultipartFile file, HttpServletRequest req) throws Exception {
			logger.info("post goods modify");
		
			// 새로운 파일이 등록되었는지 확인
			if(file.getOriginalFilename() != null && file.getOriginalFilename() != "") {
				// 기존 파일을 삭제
				new File(uploadPath + req.getParameter("gdsImg")).delete();
				new File(uploadPath + req.getParameter("gdsThumbImg")).delete();
				
				// 새로 첨부한 파일을 등록
				String imgUploadPath = uploadPath + File.separator + "imgUpload";
				String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
				String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
				
			//	vo.setGdsImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
				//vo.setGdsThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
				
			} else {  // 새로운 파일이 등록되지 않았다면
				// 기존 이미지를 그대로 사용
				//vo.setGdsImg(req.getParameter("gdsImg"));
				//vo.setGdsThumbImg(req.getParameter("gdsThumbImg"));
				
			}
			
			adminService.goodsModify(vo);
			
			return "redirect:/admin/index";
		}
	
		
		// 상품 삭제
		@RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
		public String postGoodsDelete(@RequestParam("n") int gdsNum) throws Exception {
		// @RequestParam("n")으로 인해, URL주소에 있는 n의 값을 가져와 gdsNum에 저장
		
			logger.info("post goods delete");
			System.out.println(gdsNum);
			adminService.goodsDelete2(gdsNum);
			return "redirect:/admin/index";
		}

		// 주문 목록
		@RequestMapping(value = "/shop/orderList", method = RequestMethod.GET)
		public void getOrderList(Model model) throws Exception {
			logger.info("get order list");
					
			List<OrderVO> orderList = adminService.orderList();
			
			model.addAttribute("orderList", orderList);
		}
		 
		// 주문 상세 목록
		@RequestMapping(value = "/shop/orderView", method = RequestMethod.GET)
		public void getOrderList(@RequestParam("n") String orderId,
								OrderVO order, Model model) throws Exception {
			logger.info("get order view");
			
			order.setOrderId(orderId);		
			List<OrderListVO> orderView = adminService.orderView(order);
			
			model.addAttribute("orderView", orderView);
		}
		
		// 주문 상세 목록 - 상태 변경
		@RequestMapping(value = "/shop/orderView", method = RequestMethod.POST)
		public String delivery(OrderVO order) throws Exception {
			logger.info("post order view");
					
			adminService.delivery(order);
			
			// 새로운 Service → DAO → Mapper 를 사용하지 않고, 기존에 있던 Service를 사용
			List<OrderListVO> orderView = adminService.orderView(order);	
			
			// 생성자 사용
			GoodsVO goods = new GoodsVO();
					
			for(OrderListVO i : orderView) {
				goods.setGdsNum(i.getGdsNum());

				adminService.changeStock(goods);
			}
		
			return "redirect:/admin/shop/orderView?n=" + order.getOrderId();
		}
		
		// 모든 댓글 목록 보기(관리자)
		@RequestMapping(value = "/shop/allReply", method = RequestMethod.GET)
		public void getAllReply(Model model) throws Exception {
			logger.info("get all reply");
					
			List<ReplyListVO> reply = adminService.allReply();
			
			model.addAttribute("reply", reply);
		}

		// 모든 댓글 목록 보기(관리자)
		@RequestMapping(value = "/shop/allReply", method = RequestMethod.POST)
		public String postAllReply(ReplyVO reply) throws Exception {
			logger.info("post all reply");
					
			adminService.deleteReply(reply.getRepNum());
			
			return "redirect:/admin/shop/allReply";
		}
		
		// 모든 회원 목록
		@RequestMapping(value = "/mem/memlist", method = RequestMethod.GET)
		public void getMemList(Model model) throws Exception {
			logger.info("get mem list");
			
			List<MemberVO> list = adminService.memlist();  // MemberVO형태의 List형 변수 list 선언
			
			model.addAttribute("memlist", list);  // 변수 list의 값을 list 세션에 부여
			
		}
		
		// 모든 관리자 목록
		@RequestMapping(value = "/mem/admlist", method = RequestMethod.GET)
		public void getAdmList(Model model) throws Exception {
			logger.info("get adm list");
			
			List<MemberVO> list = adminService.admlist();  // MemberVO형태의 List형 변수 list 선언
			
			model.addAttribute("admlist", list);  // 변수 list의 값을 list 세션에 부여
			
		}
		
		// 사진 이미지 받기(ajax)
		@RequestMapping(value = "/getImages", method = RequestMethod.POST)
		@ResponseBody
		public ResponseEntity<List<GoodsViewVO>> getImages(@RequestBody GoodsViewVO vo) throws Exception {
			logger.info("get goodsImagesList");
			Map<String, String> map = new Hashtable<String, String>();
			// 파라미터 값 받음 46
			int gdsNum = vo.getGdsNum();
			System.out.println(gdsNum);

			List<GoodsViewVO> goodsImagesList = adminService.goodsImagesList(gdsNum);
			
			return new ResponseEntity<List<GoodsViewVO>>(goodsImagesList, HttpStatus.OK);
			
//			System.out.println(goodsImagesList);
//			GoodsViewVO[] ar = new GoodsViewVO[goodsImagesList.size()]; 
//			for (GoodsViewVO vo2: goodsImagesList) {
//				System.out.println(vo2.getImagePath()+"d");		
//			}
//			if(!goodsImagesList.isEmpty()) {
//				GoodsViewVO[] newAr = goodsImagesList.toArray(ar);
//				for(int i =0; i <newAr.length; i++) {
//				map.put("img"+i, "<img src=/display?fileName='"+newAr[i].getImagePath()+"'>");
//				}
//
//				return map;
//				
//			} else  {
//				System.out.println("등억산게 없다");
//
//				return null;
//			}
			
		}
}
