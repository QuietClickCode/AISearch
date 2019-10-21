package com.zjj.aisearch.service;

import com.zjj.aisearch.model.*;

import java.util.List;
import java.util.Map;

/**
 * @program: aisearch
 * @description: service
 * @author: zjj
 * @create: 2019-09-07 17:16:18
 **/
public interface IndexService {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);

    Article search(int keyword);
    List<Item> searchItem(String keyword);

    int insertBrowserInfo(BrowserInfo browserInfo);

    int insertLocation(Location location);

    int insertAiNote(AiNote aiNote);


    User selectUserByUserName(String username);

    int insertUser(User user);

    List<UserLocation> selectUserLocation();

    int insertLoginLog(LoginLog loginLog);

    List<LoginLogLocation> selectLoginLocation();
    List<LogoutLogList> selectLogoutLogList();

    int insertLogoutLog(LogoutLog logoutLog);


    int insertSystemLog(SystemLog systemLog);

    Integer selectSystemLogList();

    List<JianShuArticle> searchJianShuArticle(String keyword);

    List<ZhiHuArticle> searchZhiHuArticle(String title);

    List<AiNoteList> selectAiNoteList();

    List<SearchRecordList> selectSearchRecordList();

    List<EditorList> selectEditorList();

    List<MarkDownList> selectMarkDownList();

    int validateUsername(String username);

    String selectPermission(Integer userId);

    List<Article> queryArticle(Map<String, String> map);
}
