package com.zjj.aisearch.service.impl;

import com.zjj.aisearch.mapper.IndexMapper;
import com.zjj.aisearch.model.*;
import com.zjj.aisearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: aisearch
 * @description:
 * @author: zjj
 * @create: 2019-09-07 17:17:08
 **/

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexMapper indexMapper;

    @Override
    public User index() {
        return indexMapper.index();
    }

    @Override
    public int insertSearchRecord(SearchRecord searchRecord) {
        return indexMapper.insertSearchRecord(searchRecord);
    }

    @Override
    public Article search(int keyword) {
        return indexMapper.search(keyword);
    }

    @Override
    public List<Item> searchItem(String keyword) {

        return indexMapper.searchItem(keyword);
    }

    @Override
    public int insertBrowserInfo(BrowserInfo browserInfo) {
        return indexMapper.insertBrowserInfo(browserInfo);
    }

    @Override
    public int insertLocation(Location location) {
        return indexMapper.insertLocation(location);
    }

    @Override
    public int insertAiNote(AiNote aiNote) {
        return indexMapper.insertAiNote(aiNote);
    }


    @Override
    public User selectUserByUserName(String username) {
        return indexMapper.selectUserByUserName(username);
    }

    @Override
    public int insertUser(User user) {
        return indexMapper.insertUser(user);
    }

    @Override
    public List<UserLocation> selectUserLocation() {
        return indexMapper.selectUserLocation();
    }

    @Override
    public int insertLoginLog(LoginLog loginLog) {
        return indexMapper.insertLoginLog(loginLog);
    }

    @Override
    public List<LoginLogLocation> selectLoginLocation() {
        return indexMapper.selectLoginLogLocation();
    }

    @Override
    public List<LogoutLogList> selectLogoutLogList() {
        return indexMapper.selectLogoutLogList();
    }

    @Override
    public int insertLogoutLog(LogoutLog logoutLog) {
        return indexMapper.insertLogoutLog(logoutLog);
    }

    @Override
    public int insertSystemLog(SystemLog systemLog) {
        return indexMapper.insertSystemLog(systemLog);
    }

    @Override
    public List<SystemLogList> selectSystemLogList() {
        return indexMapper.selectSystemLogList();
    }

    @Override
    public List<JianShuArticle> searchJianShuArticle(String keyword) {
        return indexMapper.searchJianShuArticle(keyword);
    }

    @Override
    public List<ZhiHuArticle> searchZhiHuArticle(String title) {
        return indexMapper.searchZhiHuArticle(title);
    }

    @Override
    public List<Article> searchArticle(String title) {
        return indexMapper.searchArticle(title);
    }

    @Override
    public List<AiNoteList> selectAiNoteList() {
        return indexMapper.selectAiNoteList();
    }

    @Override
    public List<SearchRecordList> selectSearchRecordList() {
        return indexMapper.selectSearchRecordList();
    }

    @Override
    public List<EditorList> selectEditorList() {
        return indexMapper.selectEditorList();
    }

    @Override
    public List<MarkDownList> selectMarkDownList() {
        return indexMapper.selectMarkDownList();
    }

}
