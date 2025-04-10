package com.example.MyFinance.controller;

import com.example.MyFinance.model.AccountRecord;
import com.example.MyFinance.repository.AccountRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/records")

public class AccountController {
    private final AccountRepository accountRepository;
    public AccountController(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }


    @PostMapping
    public String addRecord(@RequestBody AccountRecord record){
        System.out.println("✅ 收到請求！");
        System.out.println(record);
        if (record.getDay()==null){
            record.setDay(LocalDate.now());
        }
        accountRepository.save(record);
        return "成功新增1筆記錄";
    }


    @GetMapping("/delete/{id}") /// 刪除
    public String deleteRecord(@PathVariable Long id){
        accountRepository.deleteById(id);
        return "成功刪除ID為"+id+"的記錄✅";
    }

    @GetMapping("/category")
    public List<AccountRecord>getByCategory(@RequestParam String category){
        return accountRepository.findByCategory(category);
    }

    @GetMapping("/between")
    public List<AccountRecord>getByBetween
            (@RequestParam("start")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start,
             @RequestParam("end")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end
             ){
        return accountRepository.findByDayBetween(start,end);
    }

    @GetMapping("/type")
    public List<AccountRecord>getByType(@RequestParam String type){
        return accountRepository.findByType(type);
    }

    @GetMapping("/search")
    public List<AccountRecord>searchByNote(String note){
        return accountRepository.findByNoteContaining(note);
    }

    @GetMapping("")
    public List<AccountRecord> getAllRecords() {
        return accountRepository.findAllByOrderByDayDesc();
    }

    @PutMapping("/{id}")
    public String updateRecord(@PathVariable Long id,@RequestBody AccountRecord newRecord){
        return accountRepository.findById(id).map(existingRecord->{
            existingRecord.setType(newRecord.getType());
            existingRecord.setCategory(newRecord.getCategory());
            existingRecord.setAmount(newRecord.getAmount());
            existingRecord.setNote(newRecord.getNote());
            existingRecord.setDay(newRecord.getDay());
            accountRepository.save(existingRecord);
            return "已更新ID"+id+"的記錄";
        })
                .orElse("❌找不到ID為"+id+"的記錄");
    }


}
