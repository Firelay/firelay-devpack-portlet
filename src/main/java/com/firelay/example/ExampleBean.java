package com.firelay.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.portlet.RenderRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@RequestScoped
@ManagedBean
public class ExampleBean {

  private String username;
  private User user;

  public ExampleBean(){
    FacesContext facesContext = FacesContext.getCurrentInstance();
    RenderRequest renderRequest = (RenderRequest)facesContext.getExternalContext().getRequest();
    Long userId = PortalUtil.getUserId(renderRequest);
    User user = null;
    try {
      user = UserLocalServiceUtil.getUser(userId);
    } catch (PortalException e) {

    } catch (SystemException e){
      
    }
    setUser(user);
  }
  /**
   * @return the username
   */
  public String getUsername() {
    if(getUser() != null){
      return getUser().getFullName();
    }
    return "Anonymous";
  }
  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }
  /**
   * @param user the user to set
   */
  public void setUser(User user) {
    this.user = user;
  }


}
