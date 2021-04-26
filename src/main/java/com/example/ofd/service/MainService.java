package com.example.ofd.service;

import com.example.ofd.entity.Balance;
import com.example.ofd.entity.User;
import com.example.ofd.repo.BalanceRepo;
import com.example.ofd.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class MainService {

    @Autowired
    private BalanceRepo balanceRepo;

    @Autowired
    private UserRepo userRepo;

    // Добавление записи баланса
    public void add(Integer count, String type, User user) {
        List<Balance> balancesList = (List<Balance>) balanceRepo.findAll();
        long id = (balancesList.size() + 1);
        Balance balance = new Balance();
        balance.setId(id);
        balance.setBalance(count);
        balance.setType(type);
        balance.setDate(new Date());
        balance.setOwner(user);
        balanceRepo.save(balance);
    }
    // Фильтр по полю Type, сортировка по дате (начиная с последней), ограничение на вывод - 5 строк
    public static List<Balance> filterByType(List<Balance> balance,String type) {
        return balance.stream()
                .filter(balance1 -> balance1.getType().equals(type))
                .sorted(Comparator.comparing(Balance::getDate).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }
    //Редактирование баланса
    public void editBalance(Long id, Integer balance) {
        Balance balanceNum = balanceRepo.findById(id).orElseThrow(IllegalStateException::new);
        balanceNum.setBalance(balance);
        balanceRepo.save(balanceNum);
    }
    //Номер ID для пользователя
    public Long countNum() {
        long count = userRepo.findAll().size() + 1;
        return count;
    }
}
