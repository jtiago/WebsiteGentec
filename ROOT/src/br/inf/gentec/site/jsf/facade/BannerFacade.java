package br.inf.gentec.site.jsf.facade;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import br.inf.gentec.site.dao.BannerDAO;
import br.inf.gentec.site.db.ConnectionFactory;
import br.inf.gentec.site.model.Banner;

public class BannerFacade implements Serializable {

    private static final long serialVersionUID = 0xeff6c73ecf34d291L;
    private Banner banner;
    private Banner banner2;
    private Long key;
    private List<Banner> banners;

    public Banner getBanner()
    {
        return banner != null ? banner : (banner = new Banner());
    }

    public Banner getBanner2() {
        return banner2 != null ? banner2 : (banner2 = new Banner());
    }

    public Long getKey() {
        return key;
    }

    @SuppressWarnings("rawtypes")
	public void setKey(Long key) {
        if(key != null) {
            for(Iterator iterator = getBanners().iterator(); iterator.hasNext();) {
                Banner banner = (Banner)iterator.next();
                if(banner.getId() == key) {
                    banner2 = banner;
                }
            }

        }
        this.key = key;
    }

    public void setBanner2(Banner banner) {
        banner2 = banner;
    }

    public List<Banner> getBanners() {
        if(banners == null) {
            try {
                ConnectionFactory factory = new ConnectionFactory();
                Connection connection = factory.getConnection();
                BannerDAO dao = new BannerDAO(connection);
                banners = dao.findAll();
                dao = null;
                banner = null;
                banner2 = null;
                connection.close();
                connection = null;
                factory = null;
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return banners;
    }

    @SuppressWarnings("deprecation")
	public void salvar() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String tokem[] = getBanner().getUploadedFile().getFileName().split("\\.");
            String extencao = "jpg";
            if(tokem.length > 0)
            {
                extencao = tokem[tokem.length - 1];
            }
            String name = (new StringBuilder(String.valueOf(System.currentTimeMillis()))).append(".").append(extencao).toString();
            String path = request.getRealPath((new StringBuilder("/resources/carousel/")).append(name).toString());
            File image = new File(path);
            if(!image.exists()) {
                image.createNewFile();
            }
            IOUtils.copyLarge(getBanner().getUploadedFile().getInputstream(), new FileOutputStream(image));
            getBanner().setImagem(name);
            BannerDAO dao = new BannerDAO(connection);
            dao.save(getBanner());
            dao = null;
            banner = null;
            connection.close();
            connection = null;
            factory = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Banner cadastro com sucesso.", ""));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            Connection connection = factory.getConnection();
            BannerDAO dao = new BannerDAO(connection);
            dao.delete(getBanner2());
            dao = null;
            banner = null;
            banner2 = null;
            connection.close();
            connection = null;
            factory = null;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}