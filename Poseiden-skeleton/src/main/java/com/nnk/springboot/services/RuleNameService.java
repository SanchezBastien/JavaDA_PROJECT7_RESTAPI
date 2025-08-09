package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    private final RuleNameRepository ruleNameRepository;

    public RuleNameService(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    public List<RuleName> getAllRuleNames() {
        return ruleNameRepository.findAll();
    }

    public Optional<RuleName> getRuleNameById(Integer id) {
        return ruleNameRepository.findById(id);
    }

    public RuleName saveRuleName(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public void deleteRuleName(Integer id) {
        ruleNameRepository.deleteById(id);
    }
    public RuleName update(Integer id, RuleName updated) {
        RuleName existing = ruleNameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id: " + id));

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setJson(updated.getJson());
        existing.setTemplate(updated.getTemplate());
        existing.setSql(updated.getSql());       // mappé sur sqlStr via @Column(name="sqlStr")
        existing.setSqlPart(updated.getSqlPart());// mappé sur sqlPart via @Column(name="sqlPart")

        return ruleNameRepository.save(existing); // Hibernate UPDATE (entité managée)
    }
}
