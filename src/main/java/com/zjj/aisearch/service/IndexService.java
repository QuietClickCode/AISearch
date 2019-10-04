package com.zjj.aisearch.service;

import com.zjj.aisearch.model.*;

import java.util.List;

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


    List<SearchRecordLocation> selectSearchRecordLocation();

    int insertAiNote(AiNote aiNote);

    List<AiNoteLocation> selectAiNoteLocation();

    User selectUserByUserName(String username);

    int insertUser(User user);

    List<UserLocation> selectUserLocation();

    int insertLoginLog(LoginLog loginLog);

    List<LoginLogLocation> selectLoginLocation();

    int insertLogoutLog(LogoutLog logoutLog);

    List<LogoutLogLocation> selectLogoutLocation();

    int insertSystemLog(SystemLog systemLog);

    List<SystemLogList> selectSystemLogList();

    List<JianShuArticle> searchJianShuArticle(String keyword);

    List<ZhiHuArticle> searchZhiHuArticle(String title);

    List<Article> searchArticle(String title);
}
