package com.zjj.aisearch.mapper;


import com.zjj.aisearch.model.*;

import java.util.List;

public interface IndexMapper  {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);

    Article search(int keyword);
    List<Item> searchItem(String keyword);
    List<JianShuArticle> searchJianShuArticle(String keyword);

    int insertBrowserInfo(BrowserInfo browserInfo);

    int insertLocation(Location location);

    List<SearchRecordLocation> selectSearchRecordLocation();

    int insertAiNote(AiNote aiNote);

    List<AiNoteLocation> selectAiNoteLocation();

    User selectUserByUserName(String username);

    int insertUser(User user);

    List<UserLocation> selectUserLocation();

    int insertLoginLog(LoginLog loginLog);

    List<LoginLogLocation> selectLoginLogLocation();

    int insertLogoutLog(LogoutLog logoutLog);

    List<LogoutLogLocation> selectLogoutLogLocation();

    int insertSystemLog(SystemLog systemLog);

    List<SystemLogList> selectSystemLogList();


    List<ZhiHuArticle> searchZhiHuArticle(String title);

    List<Article> searchArticle(String title);
}
