package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListService {

    private final BidListRepository bidListRepository;

    public BidListService(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    public List<BidList> getAll() {
        return bidListRepository.findAll();
    }

    public Optional<BidList> getById(Integer id) {
        return bidListRepository.findById(id);
    }

    public BidList create(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    public BidList update(Integer id, BidList updated) {
        BidList existing = bidListRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid BidList Id: " + id));

        existing.setAccount(updated.getAccount());
        existing.setType(updated.getType());
        existing.setBidQuantity(updated.getBidQuantity());

        return bidListRepository.save(existing); // -> UPDATE
    }

    public void delete(Integer id) {
        bidListRepository.deleteById(id);
    }
}
