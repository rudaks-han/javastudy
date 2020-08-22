/*
 * Copyright 2016 Bill Bejeck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package rudaks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User: Bill Bejeck
 * Date: 2/21/16
 * Time: 3:36 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PurchasePattern {

    private double amount;

    public PurchasePattern(Purchase purchase) {
        this.amount = purchase.getPrice() * purchase.getQuantity();
    }
}
