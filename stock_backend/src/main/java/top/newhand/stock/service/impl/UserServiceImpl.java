package top.newhand.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.newhand.stock.constant.StockConstant;
import top.newhand.stock.mapper.SysUserMapper;
import top.newhand.stock.pojo.domain.SysUserDomain;
import top.newhand.stock.pojo.entity.SysUser;
import top.newhand.stock.service.UserService;
import top.newhand.stock.utils.IdWorker;
import top.newhand.stock.vo.R;
import top.newhand.stock.vo.ResponseCode;
import top.newhand.stock.vo.req.LoginReqVo;
import top.newhand.stock.vo.resp.LoginRespVo;
import top.newhand.stock.vo.resp.PageResult;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName UserServiceImpl
 * @Author HeXianGang
 * @Date 2024/2/4 16:24
 * @Version 1.0
 * @Description 用户服务接口实现类
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Description 根据用户名称查询用户信息
     * @Param [userName]
     * @Date 16:25 2024/2/4
     **/
    @Override
    public SysUser getUserByUserName(String userName) {
        return sysUserMapper.findByUserName(userName);
    }

    /**
     * @Description 用户登录功能
     * @Param [vo]
     * @Date 16:11 2024/2/15
     **/
    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        // 1、判断输入参数合法
        if (vo == null || StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank(vo.getPassword())) {
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        // 2、校验验证码和SessionId是否有效
        if (StringUtils.isBlank(vo.getCode()) || StringUtils.isBlank(vo.getSessionId())) {
            return R.error(ResponseCode.DATA_ERROR);
        }
        // 3、根据Rkey从Redis获取缓存的校验码
        String rCode = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + vo.getSessionId());
        if (StringUtils.isBlank(rCode) || !rCode.equalsIgnoreCase(vo.getCode())) {
            return R.error(ResponseCode.CHECK_CODE_ERROR);
        }
        // 4、根据用户名查询用户信息
        SysUser user = this.sysUserMapper.findByUserName(vo.getUsername());
        // 5、判断用户是否存在
        if (user == null) {
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        // 6、如果存在获取，则获取密文密码，然后传入的明文进行匹配，判断是否匹配成功
        if (!passwordEncoder.matches(vo.getPassword(), user.getPassword())) {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        // 7、正常响应
        LoginRespVo respVo = new LoginRespVo();
        BeanUtils.copyProperties(user, respVo);
        return R.ok(respVo);

    }

    /**
     * @Description 用户验证码功能
     * @Param []
     * @Date 16:12 2024/2/15
     **/
    @Override
    public R<Map> getCaptchaCode() {
        // 参数分别为： 宽, 高，， 验证码长度，干扰线数量
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        // 设置背景颜色-清灰
        lineCaptcha.setBackground(Color.lightGray);
        //自定义校验码生成方式
//        lineCaptcha.setGenerator(new CodeGenerator() {
//            @Override
//            public String generate() {
//                return RandomStringUtils.randomNumeric(4);
//            }
//            @Override
//            public boolean verify(String code, String userInputCode) {
//                return code.equalsIgnoreCase(userInputCode);
//            }
//        });
        //获取图片中的验证码，默认生成的校验码包含文字和数字，长度为4
        String checkCode = lineCaptcha.getCode();
        //生成sessionId
        String sessionId = String.valueOf(idWorker.nextId());
        //将sessionId和校验码保存在redis下，并设置缓存中数据存活时间一分钟
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX + sessionId, checkCode, 1, TimeUnit.MINUTES);
        //组装响应数据
        HashMap<String, String> info = new HashMap<>();
        info.put("sessionId", sessionId);
        info.put("imageData", lineCaptcha.getImageBase64());//获取base64格式的图片数据
        //设置响应数据格式
        return R.ok(info);
    }

    @Override
    public R<PageResult<SysUserDomain>> getUsers(Integer page, Integer pageSize, String username, String nickName, String startTime, String endTime) {
        Date start = null;
        Date end = null;
        if (!"".equals(startTime)) {
            start = DateTime.parse(startTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        }
        if (!"".equals(endTime)) {
            end = DateTime.parse(startTime, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();

        }
        PageHelper.startPage(page, pageSize);
        List<SysUserDomain> users = sysUserMapper.getUsers(page, pageSize, username, nickName, start, end);
        if (CollectionUtils.isEmpty(users)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA);
        }
        PageResult<SysUserDomain> pageResult = new PageResult<>(new PageInfo<>(users));
        return R.ok(pageResult);
    }
}
