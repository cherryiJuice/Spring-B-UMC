package com.example.umc_practice.service.memberService;

import com.example.umc_practice.converter.MemberConverter;
import com.example.umc_practice.converter.MemberPreferConverter;
import com.example.umc_practice.domain.FoodCategory;
import com.example.umc_practice.domain.Member;
import com.example.umc_practice.domain.mapping.MemberPrefer;
import com.example.umc_practice.repository.FoodCategoryRepository;
import com.example.umc_practice.repository.MemberRepository;
import com.example.umc_practice.web.dto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).get();
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    public boolean isExist(Long value) {
        return foodCategoryRepository.existsById(value);
    }
}
