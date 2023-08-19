package com.cc.controller;

import com.cc.entity.po.EmailCode;
import com.cc.entity.query.EmailCodeQuery;
import com.cc.entity.vo.PaginationResultVO;
import com.cc.entity.vo.ResponseVO;
import com.cc.entity.query.SimplePage;
import com.cc.service.EmailCodeService;
import com.cc.enums.PageSize;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 邮箱验证码表Controller
 * @author: pitayafruit
 * @date: 2023/08/17
 */
@RestController
@RequestMapping("emailCode")
public class EmailCodeController extends AGlobalExceptionHandlerController {

	@Resource
	private EmailCodeService emailCodeService;

	/**
	 * 根据条件分页查询
	 */
	@RequestMapping("LoadDataList")
	public ResponseVO loadDataList(EmailCodeQuery query) {
		return getSuccessResponseVO(emailCodeService.findListByPage(query));
	}

	/**
	 * 新增
	 */
	@RequestMapping("add")
	public ResponseVO add(EmailCode bean) {
		this.emailCodeService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 新增或更新
	 */
	@RequestMapping("addOrUpdate")
	public ResponseVO addOrUpdate(EmailCode bean) {
		this.emailCodeService.addOrUpdate(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("addBatch")
	public ResponseVO addBatch(@RequestBody List<EmailCode> listBean) {
		this.emailCodeService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增或更新
	 */
	@RequestMapping("addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<EmailCode> listBean) {
		this.emailCodeService.addOrUpdateBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据EmailAndCode查询
	 */
	@RequestMapping("getByEmailAndCode")
	public ResponseVO getByEmailAndCode(String email, String code) {
		return getSuccessResponseVO(this.emailCodeService.getByEmailAndCode(email, code));
	}

	/**
	 * 根据EmailAndCode删除
	 */
	@RequestMapping("deleteByEmailAndCode")
	public ResponseVO deleteByEmailAndCode(String email, String code) {
		this.emailCodeService.deleteByEmailAndCode(email, code);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据EmailAndCode更新
	 */
	@RequestMapping("updateByEmailAndCode")
	public ResponseVO updateByEmailAndCode(EmailCode bean, String email, String code) {
		this.emailCodeService.updateByEmailAndCode(bean, email, code);
		return getSuccessResponseVO(null);
	}

}