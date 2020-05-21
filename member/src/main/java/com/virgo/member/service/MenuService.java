package com.virgo.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.virgo.common.RequestHolder;
import com.virgo.member.dto.MenuSaveParam;
import com.virgo.member.model.CompanyMenu;
import com.virgo.member.model.Menu;
import com.virgo.member.repository.CompanyMenuRepository;
import com.virgo.member.repository.MenuRepository;
import com.virgo.member.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuService {
    @Resource
    private MenuRepository menuRepository;
    @Resource
    private CompanyMenuRepository companyMenuRepository;

    public List<MenuVO> findAll() {
        List<Menu> m;
        if (Objects.equals(RequestHolder.getCompanyCode(), "1000000")) {
            m = menuRepository.findAll();
        } else {
            List<CompanyMenu> companyMenus = companyMenuRepository.findByCompanyCode(RequestHolder.getCompanyCode());
            m = companyMenus.stream().map(it -> {
                Menu menu = it.getMenu();
                BeanUtil.copyProperties(it, menu,CopyOptions.create().ignoreNullValue().setIgnoreProperties("id"));
                return menu;
            }).collect(Collectors.toList());
        }
        List<MenuVO> treeNodes = m.stream().map(it -> {
            MenuVO vo = new MenuVO();
            BeanUtils.copyProperties(it, vo);
            return vo;
        }).collect(Collectors.toList());

        return build(treeNodes);
    }

    private List<MenuVO> build(List<MenuVO> treeNodes) {
        List<MenuVO> trees = new ArrayList<>();
        for (MenuVO treeNode : treeNodes) {

            if (treeNode.getParentId() == null) {
                trees.add(treeNode);
            }

            for (MenuVO it : treeNodes) {
                if (Objects.equals(it.getParentId(), treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    public void save(MenuSaveParam menuSaveParam) {
        Menu menu;
        if (menuSaveParam.getId() != null) {
            menu = menuRepository.findById(menuSaveParam.getId()).orElse(createMenu());
        } else menu = createMenu();

        BeanUtil.copyProperties(menuSaveParam, menu, CopyOptions.create().ignoreNullValue());

        menuRepository.save(menu);
    }

    private Menu createMenu() {
        Menu menu = new Menu();
        menu.setDeleted(false);
        menu.setIsShow(true);
        menu.setKeepAlive(false);
        menu.setSort(0);
        return menu;
    }
}
