package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.dao.IDiskDao;
import com.agjava.dvdexchange.model.entity.Disk;
import com.agjava.dvdexchange.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiskService implements IDiskService {

    private IDiskDao diskDao;

    private ISecurityService securityService;

    @Override
    public void createDisk(String name) {
        User user = this.securityService.getLoggedInUser().getUser();
        Disk disk = new Disk();
        disk.setName(name);
        disk.setOwner(user);
        diskDao.createDisk(disk);
    }

    @Override
    public List<Disk> availableDisksForCurrentUser() {
        User user = this.securityService.getLoggedInUser().getUser();
        return diskDao.getDisksByUserId(user.getId());
    }
}
