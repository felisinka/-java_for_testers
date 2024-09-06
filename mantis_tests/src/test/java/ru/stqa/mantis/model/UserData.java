/*Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
        **username** | **String** |  |  [optional]
        **password** | **String** |  |  [optional]
        **realName** | **String** |  |  [optional]
        **email** | **String** |  |  [optional]
        **enabled** | **Boolean** |  |  [optional]
        **_protected** | **Boolean** |  |  [optional]
        **accessLevel** | [**AccessLevel**](AccessLevel.md) |  |  [optional]
*/

package ru.stqa.mantis.model;

public record UserData(String username, String password, String email) {

    public UserData(){
        this("","","");
    }

    public UserData withUsername(String username){
        return new UserData(username,this.password,this.email);
    }

    public UserData withPassword(String password){
        return new UserData(this.username,password,this.email);
    }

    public UserData withEmail(String email){
        return new UserData(this.username,this.password,email);
    }
}
