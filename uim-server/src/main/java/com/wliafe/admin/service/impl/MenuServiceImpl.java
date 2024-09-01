package com.wliafe.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wliafe.admin.domain.Menu;
import com.wliafe.admin.mapper.MenuMapper;
import com.wliafe.admin.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * @author wliafe
 * @date 2023/1/20 13:22
 **/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
