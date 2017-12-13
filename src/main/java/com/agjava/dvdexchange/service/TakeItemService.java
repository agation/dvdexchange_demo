package com.agjava.dvdexchange.service;

import com.agjava.dvdexchange.dao.ITakenItemDao;
import com.agjava.dvdexchange.model.CurrentUser;
import com.agjava.dvdexchange.model.entity.Disk;
import com.agjava.dvdexchange.model.entity.TakenItem;
import com.agjava.dvdexchange.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TakeItemService implements ITakeItemService {

    private ITakenItemDao takenItemDao;

    private ISecurityService securityService;

    @Override
    public List<TakenItem> takenDisksForCurrentUser() {
        CurrentUser principal = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = principal.getId();
        return this.takenItemDao.takenByUser(userId);
    }

    @Override
    public List<TakenItem> givenDisksForCurrentUser() {
        CurrentUser user = this.securityService.getLoggedInUser();
        long userId = user.getId();
        return this.takenItemDao.takenFromUser(userId);
    }

    @Override
    public void takeDisk(Disk disk) {
        CurrentUser user = this.securityService.getLoggedInUser();
        TakenItem item = new TakenItem();
        item.setUser(user.getUser());
        item.setDisk(disk);
        this.takenItemDao.save(item);
    }

    @Override
    public void returnDisk(TakenItem item) {
        log.info("TakenItemService#removeDisk {}", item);
        this.takenItemDao.remove(item);
    }
}
