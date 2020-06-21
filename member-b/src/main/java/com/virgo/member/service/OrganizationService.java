package com.virgo.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.virgo.common.RequestHolder;
import com.virgo.member.dto.OrganizationSaveParam;
import com.virgo.member.model.Organization;
import com.virgo.member.repository.OrganizationRepository;
import com.virgo.member.vo.OrganizationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrganizationService {
    @Resource
    private OrganizationRepository organizationRepository;

    public List<OrganizationVO> findAll() {
        List<Organization> m = organizationRepository.findAllByCompanyCode(RequestHolder.getCompanyCode());
        List<OrganizationVO> treeNodes = m.stream().map(it -> {
            OrganizationVO vo = new OrganizationVO();
            BeanUtils.copyProperties(it, vo);
            return vo;
        }).collect(Collectors.toList());
        return build(treeNodes);
    }



    public void save(OrganizationSaveParam menuSaveParam) {
        Organization menu;
        if (menuSaveParam.getId() != null) {
            menu = organizationRepository.findById(menuSaveParam.getId()).orElse(createMenu());
        } else menu = createMenu();
        BeanUtil.copyProperties(menuSaveParam, menu, CopyOptions.create().ignoreNullValue());
        organizationRepository.save(menu);
    }

    public void remove(Long id) {
        organizationRepository.deleteById(id);
    }


    private List<OrganizationVO> build(List<OrganizationVO> treeNodes) {
        List<OrganizationVO> trees = new ArrayList<>();
        for (OrganizationVO treeNode : treeNodes) {

            if (treeNode.getParentId() == null) {
                trees.add(treeNode);
            }

            for (OrganizationVO it : treeNodes) {
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

    private Organization createMenu() {
        Organization menu = new Organization();
        menu.setCompanyCode(RequestHolder.getCompanyCode());
        return menu;
    }
}
