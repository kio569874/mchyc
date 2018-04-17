package com.xiandaojia.controller.page;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiandaojia.common.HomePageContants;
import com.xiandaojia.common.domain.HomePageImage;
import com.xiandaojia.common.domain.NoticeInfo;
import com.xiandaojia.common.domain.PaginationDto;
import com.xiandaojia.common.enums.ImageTypeEnum;
import com.xiandaojia.common.utils.JsonBeanUtil;
import com.xiandaojia.controller.BaseController;
import com.xiandaojia.service.page.IHomePageImageService;
import com.xiandaojia.service.page.INoticeInfoService;

@RestController
@RequestMapping("homePage")
public class HomePageController extends BaseController {

	@Autowired
	IHomePageImageService homePageImageService;

	@Autowired
	INoticeInfoService noticeInfoService;

	@RequestMapping(value = "/homePageImage/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestBody String content, HttpServletRequest request) {
		try {
			HomePageImage t = JsonBeanUtil.stringToBean(HomePageImage.class, content);
			// 解析器解析request的上下文
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 先判断request中是否包涵multipart类型的数据，
			if (multipartResolver.isMultipart(request)) {
				// 再将request中的数据转化成multipart类型的数据
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 这里的name为fileItem的alias属性值，相当于form表单中name
					String name = (String) iter.next();
					// 根据name值拿取文件
					MultipartFile file = multiRequest.getFile(name);
					if (file != null) {
						// UUID
						String imageId = UUID.randomUUID().toString().replace("-", "");
						// 新名称
						String newName = imageId + HomePageContants.IAMGE_JPG;
						File serverFile = new File(HomePageContants.COMMON_URL
								.concat(HomePageContants.IAMGE_HOMEPAGE_URL).concat(newName));
						if (!serverFile.getParentFile().exists()) {
							// 如果目标文件所在的目录不存在，则创建父目录
							serverFile.getParentFile().mkdirs();
						}
						// 1.上传文件
						file.transferTo(serverFile);
						t.setImageId(imageId);
						t.setImgUrl(HomePageContants.IAMGE_HOMEPAGE_URL.concat(newName));
						t.setType(ImageTypeEnum.IAMGE_01.getType());
						homePageImageService.insert(t);
						return getSuccessResultMsg();
					}
				}
			}
			return getErrorResultMsg("轮播图上传失败");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/homePageImage/insert", method = RequestMethod.POST)
	@ResponseBody
	public String homePageImageInsert(@RequestBody String content) {
		try {
			HomePageImage t = JsonBeanUtil.stringToBean(HomePageImage.class, content);
			String imageId = UUID.randomUUID().toString().replace("-", "");
			String newName = imageId + HomePageContants.IAMGE_JPG;
			t.setImageId(imageId);
			t.setImgUrl(HomePageContants.IAMGE_HOMEPAGE_URL.concat(newName));
			t.setType(ImageTypeEnum.IAMGE_01.getType());
			homePageImageService.insert(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/homePageImage/delete", method = RequestMethod.POST)
	@ResponseBody
	public String homePageImageDelete(@RequestBody String content) {
		try {
			HomePageImage t = JsonBeanUtil.stringToBean(HomePageImage.class, content);
			homePageImageService.delete(t.getId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/homePageImage/update", method = RequestMethod.POST)
	@ResponseBody
	public String homePageImageUpdate(@RequestBody String content) {
		try {
			HomePageImage t = JsonBeanUtil.stringToBean(HomePageImage.class, content);
			homePageImageService.update(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/homePageImage/queryListByPage", method = RequestMethod.POST)
	@ResponseBody
	public String homePageImageQueryListByPage(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			PaginationDto<HomePageImage> paginationDto = homePageImageService.querySystemUserListByPage(page, pageSize,
					null);
			return getSuccessResultMsg(JSONObject.toJSONString(paginationDto));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

	@RequestMapping(value = "/noticeInfo/insert", method = RequestMethod.POST)
	@ResponseBody
	public String noticeInfoInsert(@RequestBody String content) {
		try {
			NoticeInfo t = JsonBeanUtil.stringToBean(NoticeInfo.class, content);
			noticeInfoService.insert(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/noticeInfo/delete", method = RequestMethod.POST)
	@ResponseBody
	public String noticeInfoDelete(@RequestBody String content) {
		try {
			NoticeInfo t = JsonBeanUtil.stringToBean(NoticeInfo.class, content);
			noticeInfoService.delete(t.getId());
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/noticeInfo/update", method = RequestMethod.POST)
	@ResponseBody
	public String noticeInfoUpdate(@RequestBody String content) {
		try {
			NoticeInfo t = JsonBeanUtil.stringToBean(NoticeInfo.class, content);
			noticeInfoService.update(t);
			return getSuccessResultMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}
	}

	@RequestMapping(value = "/noticeInfo/queryListByPage", method = RequestMethod.POST)
	@ResponseBody
	public String noticeInfoQueryListByPage(@RequestBody String content) {
		try {
			JSONObject jsonObj = JSONObject.parseObject(content);
			int page = jsonObj.getInteger("page");
			int pageSize = jsonObj.getInteger("pageSize");
			PaginationDto<NoticeInfo> paginationDto = noticeInfoService.querySystemUserListByPage(page, pageSize, null);
			List<NoticeInfo> list = paginationDto.getData();
			if (list != null && list.size() > 0) {
				JSONObject listData = new JSONObject();
				JSONArray jsonArr = (JSONArray) JSONArray.toJSON(list);
				listData.put("listData", jsonArr);
				return getSuccessResultMsg(listData.toJSONString());
			} else {
				return getSuccessResultMsg(new JSONObject().toJSONString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return getErrorResultMsg(e.getMessage());
		}

	}

}
