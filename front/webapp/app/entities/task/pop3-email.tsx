import React, { useState, useEffect } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import axios from 'axios';

const apiUrl = 'api/tasks';

const POP3Modal = () => {
    const [modal, setModal] = useState(false);
    const [mailContent, setMailContent] = useState('');
    
    const toggle = () => {
        setModal(!modal);
    };

    useEffect(() => {
        if (modal) {
            const requestUrl = `${apiUrl}/mail-last`;
            axios.get(requestUrl, { params: { ptcl: 'pop3' } })
                .then(response => {
                    setMailContent(response.data);
                })
                .catch(error => {
                    setMailContent('Error fetching mail content');
                });
        }
    }, [modal]);

    return (
        <div>
            <Button className="me-2" color="warning" onClick={toggle}>
                Show Last Email(POP3)
            </Button>

            <Modal isOpen={modal} toggle={toggle}>
                <ModalHeader toggle={toggle}>Last Email</ModalHeader>
                <ModalBody>
                    {mailContent || 'Loading...'}
                </ModalBody>
                <ModalFooter>
                    <Button color="secondary" onClick={toggle}>
                        Close
                    </Button>
                </ModalFooter>
            </Modal>
        </div>
    );
};

export default POP3Modal;
