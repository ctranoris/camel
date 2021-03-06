/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.pulsar.utils.consumers;

import org.apache.camel.component.pulsar.PulsarConsumer;
import org.apache.camel.component.pulsar.PulsarEndpoint;
import org.apache.camel.component.pulsar.PulsarMessageListener;
import org.apache.camel.component.pulsar.configuration.PulsarConfiguration;
import org.apache.pulsar.client.api.ConsumerBuilder;

public final class CommonCreationStrategyImpl {
    
    private CommonCreationStrategyImpl() {
    }

    public static ConsumerBuilder<byte[]> create(final String name, final PulsarEndpoint pulsarEndpoint, final PulsarConsumer pulsarConsumer) {
        final PulsarConfiguration endpointConfiguration = pulsarEndpoint.getPulsarConfiguration();

        return pulsarEndpoint.getPulsarClient().newConsumer().topic(pulsarEndpoint.getUri()).subscriptionName(endpointConfiguration.getSubscriptionName())
            .receiverQueueSize(endpointConfiguration.getConsumerQueueSize()).consumerName(name)
            .messageListener(new PulsarMessageListener(pulsarEndpoint, pulsarConsumer.getExceptionHandler(), pulsarConsumer.getProcessor()));
    }
}
